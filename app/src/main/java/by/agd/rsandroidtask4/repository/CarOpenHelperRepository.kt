package by.agd.rsandroidtask4.repository

import by.agd.rsandroidtask4.database.CarContract.CarEntry
import by.agd.rsandroidtask4.database.CarDatabase
import by.agd.rsandroidtask4.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarOpenHelperRepository : BaseCarRepository {

    override suspend fun getAll(): List<Car> {
        val cars = mutableListOf<Car>()

        CarDatabase.getInstance()?.readableDatabase.use { db ->
            val cursor = db?.query(CarEntry.TABLE_NAME, null, null, null, null, null, null)
            cursor?.use {
                while (it.moveToNext()) {
                    val car = Car.fromCursor(it)
                    cars.add(car)
                }
            }
        }
        return cars
    }

    override suspend fun insert(car: Car): Long? = withContext(Dispatchers.IO) {
        CarDatabase.getInstance()?.writableDatabase?.use { db ->
            db.insert(CarEntry.TABLE_NAME, null, car.toContentValues())
        }
    }
}