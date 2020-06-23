package by.agd.rsandroidtask4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.databinding.ItemCarBinding
import by.agd.rsandroidtask4.model.Car
import java.text.DecimalFormat

class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        binding.car = car
    }

    private fun Float.format(pattern: String): String {
        return DecimalFormat(pattern).format(this)
    }

    companion object {

        fun inflate(parent: ViewGroup): CarViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCarBinding.inflate(inflater, parent, false)
            return CarViewHolder(binding)
        }

    }

}