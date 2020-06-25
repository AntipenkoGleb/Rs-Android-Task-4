package by.agd.rsandroidtask4.view.carlist

import androidx.lifecycle.*
import by.agd.rsandroidtask4.model.Car
import by.agd.rsandroidtask4.repository.BaseCarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarListViewModel(private val repository: BaseCarRepository) : ViewModel() {

    private val viewModelCoroutineContext = viewModelScope.coroutineContext + Dispatchers.IO

    private var _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> = _cars

    fun onViewCreated() {
        loadCarList()
    }

    private fun loadCarList() {
        viewModelScope.launch(viewModelCoroutineContext) {
            _cars.postValue(repository.getAll())
        }
    }
}


