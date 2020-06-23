package by.agd.rsandroidtask4.adapter

import android.content.Context
import androidx.annotation.LayoutRes
import by.agd.rsandroidtask4.adapter.base.DataBindingDropDownListAdapter
import by.agd.rsandroidtask4.databinding.ListItemBinding

class DropDownListAdapter(context: Context, @LayoutRes layoutResId: Int, items: List<String>) :
    DataBindingDropDownListAdapter<ListItemBinding>(context, layoutResId, items) {

    override fun onViewBinned(binding: ListItemBinding, item: String, position: Int) {
        binding.itemName.text = item
    }
}