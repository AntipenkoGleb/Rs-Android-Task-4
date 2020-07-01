package by.agd.rsandroidtask4.adapter.car

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.databinding.ItemCarBinding
import by.agd.rsandroidtask4.model.Car

class CarViewHolder(private val binding: ItemCarBinding) : SelectionViewHolder<Car>(binding.root) {

    private var car: Car? = null

    fun bind(car: Car, selected: Boolean) {
        this.car = car
        binding.car = car
        setActivatedState(selected)
        binding.carItem.isChecked = selected
    }

    private fun setActivatedState(isActivated: Boolean) {
        itemView.isActivated = isActivated
    }

    fun setNewPrice(){
        binding.price.background = ColorDrawable(Color.RED)
    }

    companion object {

        fun inflate(parent: ViewGroup): CarViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCarBinding.inflate(inflater, parent, false)
            return CarViewHolder(binding)
        }

    }

    override fun getItemDetail(): SelectionDetails<Car> {
        return SelectionDetails(adapterPosition, car)
    }
}

