package by.agd.rsandroidtask4.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.agd.rsandroidtask4.model.Car

class CarListAdapter(private val list: List<Car>) : RecyclerView.Adapter<CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}

