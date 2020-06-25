package by.agd.rsandroidtask4.repository

import androidx.lifecycle.LiveData
import by.agd.rsandroidtask4.model.Car

interface BaseCarRepository {

    suspend fun getAll(): List<Car>

    suspend fun insert(car: Car): Long?

}