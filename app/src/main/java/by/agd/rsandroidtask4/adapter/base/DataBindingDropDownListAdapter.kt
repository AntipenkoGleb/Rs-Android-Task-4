package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingDropDownListAdapter<T : ViewDataBinding>(
    context: Context,
    @LayoutRes private val layoutResId: Int,
    items: List<String>
) : FilteredBaseDropDownListAdapter(context, items) {

    private fun bindView(view: View?): T {
        return (if (view == null) {
            val inflater = LayoutInflater.from(getContext())
            DataBindingUtil.inflate<T>(inflater, layoutResId, null, false)
        } else {
            DataBindingUtil.bind(view)
        })!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        val binding = bindView(convertView)
        onViewBinned(binding, item, position)
        return binding.root
    }

    abstract fun onViewBinned(binding: T, item: String, position: Int)
}