package by.agd.rsandroidtask4.adapter.car

import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.model.Car

class CarListAdapter(private var items: List<Car>) : RecyclerView.Adapter<CarViewHolder>() {

    fun setItems(items: List<Car>) {
        this.items = items
        notifyDataSetChanged()
    }

    private var tracker: SelectionTracker<Car>? = null
    fun setTracker(tracker: SelectionTracker<Car>) {
        this.tracker = tracker
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = items[position]
        holder.setActivatedState(tracker?.isSelected(items[position]) ?: false)
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

class CarKeyProvider(private val items: List<Car>) : ItemKeyProvider<Car>(SCOPE_CACHED) {
    override fun getKey(position: Int) = items.getOrNull(position)
    override fun getPosition(key: Car) = items.indexOf(key)
}

class CarLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Car>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Car>? {
        return recyclerView.findChildViewUnder(e.x, e.y)?.let {
            (recyclerView.getChildViewHolder(it) as? ViewHolderWithDetails<Car>)?.getItemDetail()
        }
    }
}

interface ViewHolderWithDetails<TItem> {

    fun getItemDetail(): ItemDetailsLookup.ItemDetails<TItem>
}

class CarDetails(private val adapterPosition: Int, private val selectedKey: Car?) :
    ItemDetailsLookup.ItemDetails<Car>() {

    override fun getSelectionKey() = selectedKey

    override fun getPosition() = adapterPosition

}



