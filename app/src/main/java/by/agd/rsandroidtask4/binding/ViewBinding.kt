package by.agd.rsandroidtask4.binding

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod
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
    "setSelected",
    "selectedAttrChanged",
    requireAll = false
)
fun setItemList(
    view: AutoCompleteTextView,
    itemArray: Array<String>,
    imageArray: TypedArray?,
    selectedItemId: Int?,
    selectedAttrChanged: InverseBindingListener?
) {
    val textInputLayout = view.parent.parent as TextInputLayout

    val adapter = getAdapter(view.context, itemArray, imageArray)
    view.setAdapter(adapter)

    if (selectedItemId == null || selectedItemId < 0) {
        view.setText("")
    } else {
        val item = itemArray[selectedItemId]
        if (view.text.toString() != item) {
            if (imageArray != null)
                textInputLayout.startIconDrawable = imageArray.getDrawable(selectedItemId)
            view.setText(item)
        }
    }

    view.setOnItemClickListener { _view, _, _, id ->
        if (imageArray != null)
            textInputLayout.startIconDrawable = imageArray.getDrawable(id.toInt())

        clearFocusAndHideKeyboard(_view)

        selectedAttrChanged?.onChange()
    }
}

@InverseBindingAdapter(attribute = "setSelected", event = "selectedAttrChanged")
fun getSelected(view: AutoCompleteTextView): Int {
    val adapter = view.adapter as DataBindingArrayAdapter<*, *>
    return adapter.getIdByText(view.text.toString())
}

private fun getAdapter(
    context: Context,
    itemArray: Array<String>,
    imageArray: TypedArray?
): ArrayAdapter<*> {
    return if (imageArray == null) {
        DataBindingArrayAdapter<String, ListItemBinding>(context, R.layout.list_item, itemArray)
            .onViewBinded { binding, item, _ -> binding.itemName.text = item }
    } else {
        val items = itemArray.mapIndexed { index, item ->
            ImageListItem(item, imageArray.getDrawable(index))
        }.toTypedArray()

        DataBindingArrayAdapter<ImageListItem<String, Drawable?>, ListImageItemBinding>(
            context,
            R.layout.list_image_item,
            items
        ).onViewBinded { binding, item, _ ->
            binding.itemName.text = item.first
            binding.itemImage.setImageDrawable(item.second)
        }
    }
}


private fun clearFocusAndHideKeyboard(view: View) {
    val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}

data class ImageListItem<out A, out B>(val first: A, val second: B) : Serializable {
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

//    @BindingAdapter("setSelected")
//    @JvmStatic
//    fun setSelected(view: AutoCompleteTextView, newValue: Int) {
//        val adapter = view.adapter as DataBindingArrayAdapter<*, *>
//        if (newValue > 0) {
//            val item = adapter.getItemById(newValue)
//            if (view.text.toString() != item.toString()) {
//                view.setText(item.toString())
//            }
//        } else
//            view.setText("")
//    }


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
}