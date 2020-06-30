package by.agd.rsandroidtask4.adapter.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.databinding.ItemCarBinding
import by.agd.rsandroidtask4.model.Car

class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root), ViewHolderWithDetails<Car> {

    private var car: Car? = null

    fun bind(car: Car) {
        this.car = car
        binding.car = car
    }

    fun setActivatedState(isActivated: Boolean) {
        itemView.isActivated = isActivated
    }

    companion object {

        fun inflate(parent: ViewGroup): CarViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCarBinding.inflate(inflater, parent, false)
            return CarViewHolder(binding)
        }

    }

    override fun getItemDetail(): CarDetails {
        return CarDetails(adapterPosition, car)
    }
}

