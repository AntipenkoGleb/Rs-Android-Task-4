package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingDropDownListAdapter<T, T1 : ViewDataBinding>(
    context: Context,
    @LayoutRes private val layoutResId: Int,
    items: List<T>
) : FilteredBaseDropDownListAdapter<T>(context, items) {

    private fun bindView(view: View?): T1 {
        return (if (view == null) {
            val inflater = LayoutInflater.from(getContext())
            DataBindingUtil.inflate<T1>(inflater, layoutResId, null, false)
        } else {
            DataBindingUtil.bind(view)
        })!!
    }

    private var onViewBinnedCallback: ((binding: T1, item: T, position: Int) -> Unit)? = null

    public fun onViewBinned(callback: (binding: T1, item: T, position: Int) -> Unit): DataBindingDropDownListAdapter<T, T1> {
        onViewBinnedCallback = callback
        return this
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        val binding = bindView(convertView)
        onViewBinnedCallback?.invoke(binding, item, position)
        return binding.root
    }
}