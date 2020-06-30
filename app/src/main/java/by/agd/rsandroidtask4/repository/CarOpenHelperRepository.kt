package by.agd.rsandroidtask4.repository

import android.content.Context
import androidx.annotation.StringRes
import androidx.preference.PreferenceManager
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.database.CarContract.CarEntry
import by.agd.rsandroidtask4.database.CarDatabase
import by.agd.rsandroidtask4.model.Car

class CarOpenHelperRepository(private val context: Context) : BaseCarRepository {

    override suspend fun getAll(): List<Car> {
        val cars = mutableListOf<Car>()

        val sortQuery = getSortQuery()
        val filterQuery = getFilterQuery()

        CarDatabase.getInstance()?.readableDatabase.use { db ->
            val cursor =
                db?.query(CarEntry.TABLE_NAME, null, filterQuery, null, null, null, sortQuery)
            cursor?.use {
                while (it.moveToNext()) {
                    val car = Car.fromCursor(it)
                    cars.add(car)
                }
            }

        }
        return cars
    }

    private fun getSortQuery(): String? {
        var sortQuery: String? = null
        val sortingEnabled = getPreferenceValue<Boolean>(R.string.pref_sorting_enabled_key)
        if (sortingEnabled != null && sortingEnabled) {
            val sortingField = getPreferenceValue<String>(R.string.pref_sorting_field_key)
            val sortingOrder = getPreferenceValue<String>(R.string.pref_sorting_order_key)
            if (sortingField != null && sortingOrder != null)
                sortQuery = "$sortingField $sortingOrder"
        }
        return sortQuery
    }

    private fun getFilterQuery(): String {
        val filter = mutableListOf<String>()
        val filteringEnabled = getPreferenceValue<Boolean>(R.string.pref_filtering_enabled_key)
        if (filteringEnabled != null && filteringEnabled) {
            val filterFields = mapOf(
                CarEntry.COLUMN_NAME_CAR_MAKER to R.string.pref_filtering_car_maker_key,
                CarEntry.COLUMN_NAME_COLOR to R.string.pref_filtering_color_key,
                CarEntry.COLUMN_NAME_BODY to R.string.pref_filtering_body_key,
                CarEntry.COLUMN_NAME_TRANSMISSION to R.string.pref_filtering_transmission_key,
                CarEntry.COLUMN_NAME_DRIVETRAIN to R.string.pref_filtering_drivetrain_key
            )

            for (field in filterFields) {
                val value = getPreferenceValue<Set<String>>(field.value) ?: continue
                val args = value.joinToString(separator = ", ", prefix = "(", postfix = ")")
                val query = "${field.key} IN $args"
                filter.add(query)
            }
        }
        return filter.joinToString(" AND ")
    }

    private inline fun <reified T> getPreferenceValue(@StringRes keyId: Int): T? {
        val resources = context.resources
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val key = resources.getString(keyId)
        return when (T::class) {
            Boolean::class -> preferences.getBoolean(key, false) as T
            String::class -> preferences.getString(key, null) as T
            Set::class -> preferences.getStringSet(key, null) as T
            else -> null
        }
    }

    override suspend fun insert(car: Car): Long? {
        return CarDatabase.getInstance()?.writableDatabase?.use { db ->
            db.insert(CarEntry.TABLE_NAME, null, car.toContentValues())
        }
    }
}