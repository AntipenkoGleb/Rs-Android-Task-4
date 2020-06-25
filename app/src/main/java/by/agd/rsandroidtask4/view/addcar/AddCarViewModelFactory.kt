package by.agd.rsandroidtask4.view.addcar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.agd.rsandroidtask4.repository.BaseCarRepository

class AddCarViewModelFactory(private val repository: BaseCarRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddCarViewModel(repository) as T
    }

}