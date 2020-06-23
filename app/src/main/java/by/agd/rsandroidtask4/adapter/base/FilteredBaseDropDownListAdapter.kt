package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable

abstract class FilteredBaseDropDownListAdapter(
    private val context: Context,
    private val items: List<String>
) : BaseAdapter(), Filterable {

    protected fun getContext() = context

    private val filteredItems: MutableList<String> = ArrayList(items)

    override fun getItem(position: Int): String = items[position]

    override fun getItemId(position: Int): Long = items.indexOf(items[position]).toLong()

    override fun getCount(): Int = items.size

    fun resetFilter() = clearAndAddAll(items)

    private fun clearAndAddAll(collection: Collection<String>) {
        filteredItems.clear()
        filteredItems.addAll(collection)
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredItems = if (constraint.isNullOrBlank()) items
            else items.filter { it.startsWith(constraint, true) }

            return FilterResults().apply {
                values = filteredItems
                count = filteredItems.size
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null && results.count > 0) {
                clearAndAddAll(results.values as Collection<String>)
                notifyDataSetChanged()
            }
        }
    }
}