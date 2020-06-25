package by.agd.rsandroidtask4.view.addcar

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.agd.rsandroidtask4.model.Car
import by.agd.rsandroidtask4.repository.BaseCarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCarViewModel(private val repository: BaseCarRepository) : ViewModel() {

    private val viewModelCoroutineContext = viewModelScope.coroutineContext + Dispatchers.IO

    val car = MutableLiveData(Car())


    fun addCar() {
        viewModelScope.launch(viewModelCoroutineContext) {
            repository.insert(car.value!!)
        }
    }

    fun setImage(uri: Uri) {
        car.postValue(car.apply { value?.imageUri = uri }.value)
    }

}