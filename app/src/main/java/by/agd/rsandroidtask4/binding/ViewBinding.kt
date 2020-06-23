package by.agd.rsandroidtask4.binding

import android.content.res.TypedArray
import android.net.Uri
import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.DropDownListAdapter
import by.agd.rsandroidtask4.databinding.ListItemBinding
import coil.api.load
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat


@BindingAdapter("setItemList", "setImageList", requireAll = false)
fun setItemList(view: AutoCompleteTextView, items: Array<String>, images: TypedArray?) {
    val context = view.context
    val resources = context.resources
    val textInputLayout = view.parent.parent as TextInputLayout

    val adapter =
        DropDownListAdapter<String, ListItemBinding>(context, R.layout.list_item, items.toList())
            .onViewBinned { binding, item, _ -> binding.itemName.text = item }
            .setFilter { item, constraint -> item.startsWith(constraint) }

//    val adapter = if (images == null) DropDownListAdapter<String,ListItemBinding>(context, R.layout.list_item, items.toList())
//        else DropDownImageListAdapter(context, R.layout.image_list_item, items, items.toList())

    view.apply {
        setAdapter(adapter)
        setOnItemClickListener { _, _, _, id ->
            images?.let { textInputLayout.startIconDrawable = images.getDrawable(id.toInt()) }
            adapter.resetFilter()
        }
    }
}

@BindingAdapter("setImageSrcFromUri", "placeholder", "errorDrawable", requireAll = false)
fun setImageSrcFromUri(
    view: AppCompatImageView,
    uri: Uri,
    @DrawableRes placeholderResId: Int?,
    @DrawableRes errorResId: Int?
) {
    view.load(uri) {
        placeholderResId?.let { this.placeholder(it) }
        errorResId?.let { error(it) }
    }
}

object Converter {
    @JvmStatic
    fun decimalFormat(value: Any, pattern: String): String {
        return DecimalFormat(pattern).format(value)
    }
}