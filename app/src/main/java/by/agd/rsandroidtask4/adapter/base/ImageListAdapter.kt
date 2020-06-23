package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

//abstract class ImageListAdapter<T, T1 : ViewDataBinding>(
//    context: Context,
//    @LayoutRes layoutResId: Int,
//    items: List<T>,
//    private val images: TypedArray
//) : DataBindingDropDownListAdapter<T, T1>(context, layoutResId, items) {
//
//    override fun onViewBinned(binding: T1, item: String, position: Int) {
//        val image = images.getDrawable(getItemId(position).toInt())
//        onViewBinned(binding, item, image, position)
//    }
//
//    abstract fun onViewBinned(binding: T1, item: String, image: Drawable?, position: Int)
//}