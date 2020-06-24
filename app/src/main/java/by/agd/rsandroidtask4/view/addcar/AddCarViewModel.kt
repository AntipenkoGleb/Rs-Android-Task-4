package by.agd.rsandroidtask4.view.addcar

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.agd.rsandroidtask4.model.Car

class AddCarViewModel : ViewModel() {

    val car = MutableLiveData(Car())

//    private val car: Car = Car()
//
//    @Bindable
//    fun getPrice(): Float {
//        return car.price
//    }
//
//    fun setPrice(price: Float) {
//        if (car.price != price) car.price = price
//    }
}