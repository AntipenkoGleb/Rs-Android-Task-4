package by.agd.rsandroidtask4.view.addcar

import by.agd.rsandroidtask4.model.Car

class OnAddCarClickListener(private val listener: (Car) -> Unit) {
    fun onClick(car: Car) = listener(car)
}