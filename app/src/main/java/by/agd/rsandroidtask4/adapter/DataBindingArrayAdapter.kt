package by.agd.rsandroidtask4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class DataBindingArrayAdapter<T, T1 : ViewDataBinding>(
    context: Context,
    @LayoutRes private val resId: Int,
    private val items: Array<T>
) : ArrayAdapter<T>(context, resId, items) {

    private var onViewBindedCallback: ((binding: T1, item: T, position: Int) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)!!
        val binding = bindView(convertView)!!
        onViewBindedCallback?.invoke(binding, item, position)
        return binding.root
    }

    private fun bindView(view: View?): T1? {
        return (if (view == null) {
            val inflater = LayoutInflater.from(context)
            DataBindingUtil.inflate<T1>(inflater, resId, null, false)
        } else {
            DataBindingUtil.bind(view)
        })
    }

    fun onViewBinded(callback: (binding: T1, item: T, position: Int) -> Unit): DataBindingArrayAdapter<T, T1> {
        onViewBindedCallback = callback
        return this
    }

    fun getIdByText(text: String): Int {
        val item = items.firstOrNull { it.toString().equals(text, true) } ?: return -1
        return items.indexOf(item)
    }


    fun contains(content: Any): Int {
        return items.indexOfFirst { it.toString().equals(content.toString(), true) }
    }
}
