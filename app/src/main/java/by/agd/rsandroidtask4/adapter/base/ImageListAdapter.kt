package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

abstract class ImageListAdapter<T : ViewDataBinding>(
    context: Context,
    @LayoutRes layoutResId: Int,
    items: List<String>,
    private val images: TypedArray
) : DataBindingDropDownListAdapter<T>(context, layoutResId, items) {

    override fun onViewBinned(binding: T, item: String, position: Int) {
        val image = images.getDrawable(position)
        onViewBinned(binding, item, image, position)
    }

    abstract fun onViewBinned(binding: T, item: String, image: Drawable?, position: Int)
}