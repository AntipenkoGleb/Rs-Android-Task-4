package by.agd.rsandroidtask4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class DropDownListAdapter<T, T1 : ViewDataBinding>(
    private val context: Context,
    @LayoutRes private val layoutResId: Int,
    private val items: List<T>
) : BaseAdapter(), Filterable {

    private val filteredItems: MutableList<T> = ArrayList(items)

    override fun getItem(position: Int): T = filteredItems[position]

    override fun getItemId(position: Int): Long = items.indexOf(filteredItems[position]).toLong()

    override fun getCount(): Int = filteredItems.size

    public fun getOriginalItem(position: Int): T = items[position]

    public fun getOriginalItemId(item: T) = items.indexOf(item)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        val binding = bindView(convertView)
        onViewBinnedCallback?.invoke(binding, item, position)
        return binding.root
    }

    private fun bindView(view: View?): T1 {
        return (if (view == null) {
            val inflater = LayoutInflater.from(context)
            DataBindingUtil.inflate<T1>(inflater, layoutResId, null, false)
        } else {
            DataBindingUtil.bind(view)
        })!!
    }

    private var onViewBinnedCallback: ((binding: T1, item: T, position: Int) -> Unit)? = null

    fun onViewBinned(callback: (binding: T1, item: T, position: Int) -> Unit): DropDownListAdapter<T, T1> {
        onViewBinnedCallback = callback
        return this
    }

    private var filter: Filter? = null

    override fun getFilter(): Filter? = filter

    fun setFilter(filterPredicate: (item: T, constraint: CharSequence) -> Boolean): DropDownListAdapter<T, T1> {
        filter = object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val filteredItems = if (constraint.isNullOrBlank()) items
                else items.filter { filterPredicate(it, constraint) }

                return FilterResults().apply {
                    values = filteredItems
                    count = filteredItems.size
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    clearAndAddAll(results.values as Collection<T>)
                    notifyDataSetChanged()
                } else notifyDataSetInvalidated()
            }
        }
        return this
    }

    fun resetFilter() = clearAndAddAll(items)

    private fun clearAndAddAll(collection: Collection<T>) {
        filteredItems.clear()
        filteredItems.addAll(collection)
    }

}