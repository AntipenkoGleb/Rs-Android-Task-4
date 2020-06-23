package by.agd.rsandroidtask4.adapter

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import by.agd.rsandroidtask4.adapter.base.DataBindingDropDownListAdapter

class DropDownListAdapter<T, T1 : ViewDataBinding>(
    context: Context,
    @LayoutRes layoutResId: Int,
    items: List<T>
) : DataBindingDropDownListAdapter<T, T1>(
    context,
    layoutResId,
    items
) {


}