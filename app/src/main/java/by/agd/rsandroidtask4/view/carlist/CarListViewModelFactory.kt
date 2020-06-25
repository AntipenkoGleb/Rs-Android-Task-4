package by.agd.rsandroidtask4.view.carlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.agd.rsandroidtask4.repository.BaseCarRepository

class CarListViewModelFactory(private val repository: BaseCarRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarListViewModel(repository) as T
    }
}