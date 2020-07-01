package by.agd.rsandroidtask4.adapter.car

import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.ItemKeyProvider.SCOPE_MAPPED
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView


class SelectionKeyProviders<T>(private val items: List<T>, scope: Int) : ItemKeyProvider<T>(scope) {
    override fun getKey(position: Int) = items.getOrNull(position)
    override fun getPosition(key: T) = items.indexOf(key)
}

class SelectionLookup<T>(private val recyclerView: RecyclerView) : ItemDetailsLookup<T>() {

    @Suppress("UNCHECKED_CAST")
    override fun getItemDetails(e: MotionEvent): ItemDetails<T>? {
        return recyclerView.findChildViewUnder(e.x, e.y)?.let {
            (recyclerView.getChildViewHolder(it) as? ViewHolderWithDetails<T>)?.getItemDetail()
        }
    }
}

interface ViewHolderWithDetails<T> {
    fun getItemDetail(): ItemDetailsLookup.ItemDetails<T>
}

class SelectionDetails<T>(private val adapterPosition: Int, private val selectedKey: T?) :
    ItemDetailsLookup.ItemDetails<T>() {

    override fun getSelectionKey() = selectedKey

    override fun getPosition() = adapterPosition
}

class SelectionTrackerController<T : Parcelable>(
    activity: AppCompatActivity,
    recyclerView: RecyclerView,
    items: List<T>,
    strategy: StorageStrategy<T>
) {

    private var actionMode: ActionMode? = null

    private val _tracker = getSelectionTracker(recyclerView, items, strategy)
    val tracker get() = _tracker

    private fun getSelectionTracker(
        recyclerView: RecyclerView,
        items: List<T>,
        strategy: StorageStrategy<T>
    ): SelectionTracker<T> {
        return SelectionTracker.Builder(
            "someId",
            recyclerView,
            SelectionKeyProviders<T>(items, SCOPE_MAPPED),
            SelectionLookup<T>(recyclerView),
            strategy
        ).build()
    }

    init {
        _tracker.addObserver(object : SelectionTracker.SelectionObserver<Any>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                with(activity) {
                    if (_tracker.hasSelection() && actionMode == null) {
                        actionMode = startSupportActionMode(ActionModeController(_tracker))
                        setSelectedTitle(_tracker.selection.size())
                    } else if (!_tracker.hasSelection()) {
                        actionMode?.finish()
                        actionMode = null
                    } else {
                        setSelectedTitle(_tracker.selection.size())
                    }
                }
            }
        })
    }

    private fun setSelectedTitle(selected: Int) {
        actionMode?.title = "Selected: $selected"
    }
}

abstract class SelectionViewHolder<T>(view: View) : RecyclerView.ViewHolder(view),
    ViewHolderWithDetails<T>

class ActionModeController<T>(private val tracker: SelectionTracker<T>) : ActionMode.Callback {

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return false
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        Log.d("Hello", tracker.selection.joinToString(", "))
        tracker.clearSelection()
    }
}
