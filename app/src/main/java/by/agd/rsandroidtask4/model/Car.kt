package by.agd.rsandroidtask4.model

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import androidx.core.database.getStringOrNull
import by.agd.rsandroidtask4.database.CarContract.CarEntry

data class Car(
    var id: Long = 0,
    var carMaker: Int = -1,
    var model: String = "",
    var price: Float = 0f,
    var imageUri: Uri? = null,
    var body: Int = -1,
    var color: Int = -1,
    var year: Int = -1,
    var mileage: Int = -1,
    var transmission: Int = -1,
    var drivetrain: Int = -1,
    var description: String = ""
) {
    fun toContentValues(): ContentValues {
        return ContentValues().apply {
            put(CarEntry.COLUMN_NAME_CAR_MAKER, carMaker)
            put(CarEntry.COLUMN_NAME_MODEL, model)
            put(CarEntry.COLUMN_NAME_PRICE, price)
            put(CarEntry.COLUMN_NAME_IMAGE_URI, imageUri.toString())
            put(CarEntry.COLUMN_NAME_BODY, body)
            put(CarEntry.COLUMN_NAME_COLOR, color)
            put(CarEntry.COLUMN_NAME_YEAR, year)
            put(CarEntry.COLUMN_NAME_MILEAGE, mileage)
            put(CarEntry.COLUMN_NAME_TRANSMISSION, transmission)
            put(CarEntry.COLUMN_NAME_DRIVETRAIN, drivetrain)
            put(CarEntry.COLUMN_NAME_DESCRIPTION, description)
        }
    }

    companion object {
        fun fromCursor(cursor: Cursor): Car {
            val car = Car()

            with(cursor) {
                car.apply {
                    id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    carMaker = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_CAR_MAKER))
                    model = getString(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_MODEL))
                    price = getFloat(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_PRICE))
                    imageUri = Uri.parse(getStringOrNull(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_IMAGE_URI)))
                    body = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_BODY))
                    color = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_COLOR))
                    year = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_YEAR))
                    mileage = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_YEAR))
                    transmission = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_TRANSMISSION))
                    drivetrain = getInt(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_DRIVETRAIN))
                    description = getString(getColumnIndexOrThrow(CarEntry.COLUMN_NAME_DESCRIPTION))
                }
            }

            return car
        }
    }
}
