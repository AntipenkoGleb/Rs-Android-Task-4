package by.agd.rsandroidtask4.adapter.car

import android.os.Parcelable
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.model.Car

class CarListAdapter(private var items: List<Car>) :
    SelectionRecyclerViewAdapter<Car, CarViewHolder>() {

    fun setItems(items: List<Car>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, isSelected(position))
    }

    override fun getItemCount(): Int = items.size

    override fun isSelected(position: Int): Boolean = tracker?.isSelected(items[position]) ?: false
}

abstract class SelectionRecyclerViewAdapter<T : Parcelable, VH : SelectionViewHolder<T>> : RecyclerView.Adapter<VH>() {

    private var _tracker: SelectionTracker<T>? = null

    protected val tracker get() = _tracker

    abstract fun isSelected(position: Int): Boolean

    fun setTracker(tracker: SelectionTracker<T>) {
        _tracker = tracker
    }
}



