package by.agd.rsandroidtask4.binding

import android.app.Activity
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.*
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.DataBindingArrayAdapter
import by.agd.rsandroidtask4.databinding.ListImageItemBinding
import by.agd.rsandroidtask4.databinding.ListItemBinding
import coil.api.load
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable
import java.text.DecimalFormat


@BindingAdapter(
    "setItemList",
    "setImageList",
    "selected",
    "selectedAttrChanged",
    "onItemClickListener",
    requireAll = false
)
fun setItemList(
    view: AutoCompleteTextView,
    itemArray: Array<String>,
    imageArray: TypedArray?,
    selected: Int?,
    selectedAttrChanged: InverseBindingListener?,
    onItemClickListener: AdapterView.OnItemClickListener?
) {
    val context = view.context

    val adapter = if (imageArray == null) {
        DataBindingArrayAdapter<String, ListItemBinding>(context, R.layout.list_item, itemArray)
            .onViewBinded { binding, item, _ -> binding.itemName.text = item }
    } else {
        val items = itemArray.mapIndexed { i, v -> ImageListItem(v, imageArray.getDrawable(i)) }
            .toTypedArray()

        DataBindingArrayAdapter<ImageListItem<String, Drawable?>, ListImageItemBinding>(
            context,
            R.layout.list_image_item,
            items
        ).onViewBinded { binding, item, _ ->
            binding.itemName.text = item.first
            binding.itemImage.setImageDrawable(item.second)
        }
    }

    val textInputLayout = view.parent.parent as TextInputLayout
    view.apply {
        setAdapter(adapter)
        selected?.let {
            if (view.text.toString().isBlank()) {
                if (imageArray != null)
                    textInputLayout.startIconDrawable = imageArray.getDrawable(id)
                if (selected > 0)
                    view.setText(itemArray[it])
            }
        }
        setOnItemClickListener { v, pos, p, id ->
            if (imageArray != null)
                textInputLayout.startIconDrawable = imageArray.getDrawable(id.toInt())

            clearFocusAndHideKeyboard(view)
            selectedAttrChanged?.onChange()
            onItemClickListener?.onItemClick(v, pos, p, id)
        }
    }
}

@BindingAdapter("selected")
fun setSelected(){

}

private fun clearFocusAndHideKeyboard(view: View) {
    val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}

data class ImageListItem<out A, out B>(
    val first: A,
    val second: B
) : Serializable {
    override fun toString(): String = first.toString()
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

    @BindingAdapter("selected")
    @JvmStatic
    fun setSelected(view: AutoCompleteTextView, newValue: Int) {
        val adapter = view.adapter as DataBindingArrayAdapter<*, *>
        if (newValue > 0) {
            val item = adapter.getItemById(newValue)
            if (view.text.toString() != item.toString()) {
                view.setText(item.toString())
            }
        } else
            view.setText("")
    }

    @InverseBindingAdapter(attribute = "selected", event = "selectedAttrChanged")
    @JvmStatic
    fun getSelected(view: AutoCompleteTextView): Int {
        val adapter = view.adapter as DataBindingArrayAdapter<*, *>
        return adapter.getIdByText(view.text.toString())
    }

    @JvmStatic
    @InverseMethod("floatFromString")
    fun floatToString(value: Float): String {
        return value.toString()
    }

    @JvmStatic
    fun floatFromString(value: String): Float {
        return value.toFloatOrNull() ?: 0f
    }

    @JvmStatic
    @InverseMethod("intFromString")
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    fun intFromString(value: String): Int {
        return value.toIntOrNull() ?: 0
    }

    @JvmStatic
    fun decimalFormat(value: Any, pattern: String): String {
        return DecimalFormat(pattern).format(value)
    }

    @JvmStatic
    fun debug(tag: String, message: Any) {
        Log.d(tag, message.toString())
    }
}

@BindingConversion
fun floatToString(value: Float) = value.toString()