package by.agd.rsandroidtask4.binding

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.DataBindingArrayAdapter
import by.agd.rsandroidtask4.databinding.ListImageItemBinding
import by.agd.rsandroidtask4.databinding.ListItemBinding
import coil.api.load
import com.google.android.material.textfield.TextInputLayout


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

    view.addTextChangedListener {
        val containsId = adapter.contains(it.toString())
        if (containsId >= 0) {
            if (imageArray != null)
                textInputLayout.startIconDrawable = imageArray.getDrawable(containsId)
            selectedAttrChanged?.onChange()
        }
    }

    view.setOnItemClickListener { _, _, position, _ ->
        if (imageArray != null)
            textInputLayout.startIconDrawable = imageArray.getDrawable(adapter.getItemId(position).toInt())

        selectedAttrChanged?.onChange()

        clearFocusAndHideKeyboard(view)
    }
}

@InverseBindingAdapter(attribute = "setSelected", event = "selectedAttrChanged")
fun getSelected(view: AutoCompleteTextView): Int {
    val adapter = view.adapter as DataBindingArrayAdapter<*, *>
    return adapter.getIdByText(view.text.toString().toLowerCase())
}

@BindingAdapter("setImageUri")
fun setImageUri(view: ImageView, uri: Uri) {
    view.load(uri) {
        error(R.drawable.car_placeholder)
    }
}

private fun getAdapter(
    context: Context,
    itemArray: Array<String>,
    imageArray: TypedArray?
): DataBindingArrayAdapter<*, *> {
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


