package by.agd.rsandroidtask4.adapter.base

import android.content.Context
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable

abstract class FilteredBaseDropDownListAdapter<T>(
    private val context: Context,
    private val items: List<T>
) : BaseAdapter(), Filterable {

    protected fun getContext() = context

    private val filteredItems: MutableList<T> = ArrayList(items)

    override fun getItem(position: Int): T = filteredItems[position]

    override fun getItemId(position: Int): Long = items.indexOf(filteredItems[position]).toLong()

    override fun getCount(): Int = filteredItems.size

    fun resetFilter() = clearAndAddAll(items)

    private fun clearAndAddAll(collection: Collection<T>) {
        filteredItems.clear()
        filteredItems.addAll(collection)
    }

    public fun setFilter(filterPredicate: (item: T, constraint: CharSequence) -> Boolean): FilteredBaseDropDownListAdapter<T> {
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
                }
            }
        }
        return this
    }

    private var filter: Filter? = null

    override fun getFilter(): Filter? = filter
}