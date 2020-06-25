package by.agd.rsandroidtask4.repository

import android.content.Context
import androidx.annotation.StringRes
import androidx.preference.PreferenceManager
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.database.CarContract.CarEntry
import by.agd.rsandroidtask4.database.CarDatabase
import by.agd.rsandroidtask4.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarOpenHelperRepository(private val context: Context) : BaseCarRepository {

    override suspend fun getAll(): List<Car> {
        val cars = mutableListOf<Car>()

        var sorting: String? = null
        val sortingEnabled = getPreferenceValue<Boolean>(R.string.pref_sorting_enabled_key)
        if (sortingEnabled != null && sortingEnabled) {
            val sortingField = getPreferenceValue<String>(R.string.pref_sorting_field_key)
            val sortingOrder = getPreferenceValue<String>(R.string.pref_sorting_order_key)
            if (sortingField != null && sortingOrder != null)
                sorting = "$sortingField $sortingOrder"
        }

        CarDatabase.getInstance()?.readableDatabase.use { db ->
            val cursor = db?.query(CarEntry.TABLE_NAME, null, null, null, null, null, sorting)
            cursor?.use {
                while (it.moveToNext()) {
                    val car = Car.fromCursor(it)
                    cars.add(car)
                }
            }
        }
        return cars
    }

    private inline fun <reified T> getPreferenceValue(@StringRes keyId: Int): T? {
        val resources = context.resources
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val key = resources.getString(keyId)
        return when (T::class) {
            Boolean::class -> preferences.getBoolean(key, false) as T
            String::class -> preferences.getString(key, null) as T
            else -> null
        }
    }

    override suspend fun insert(car: Car): Long? = withContext(Dispatchers.IO) {
        CarDatabase.getInstance()?.writableDatabase?.use { db ->
            db.insert(CarEntry.TABLE_NAME, null, car.toContentValues())
        }
    }
}