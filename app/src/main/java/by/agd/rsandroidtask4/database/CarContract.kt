package by.agd.rsandroidtask4.database

import android.provider.BaseColumns

object CarContract {

    const val SQL_CREATE_TABLE =
        "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "${CarEntry.COLUMN_NAME_CAR_MAKE} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_MODEL} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_PRICE} REAL NOT NULL," +
                "${CarEntry.COLUMN_NAME_IMAGE_URI} TEXT," +
                "${CarEntry.COLUMN_NAME_BODY} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_COLOR} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_YEAR} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_MILEAGE} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_TRANSMISSION} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_DRIVETRAIN} INTEGER NOT NULL," +
                "${CarEntry.COLUMN_NAME_DESCRIPTION} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"

    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_CAR_MAKE = "car_make"
        const val COLUMN_NAME_MODEL = "model"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_IMAGE_URI = "image_uri"
        const val COLUMN_NAME_BODY = "body_type"
        const val COLUMN_NAME_COLOR = "color"
        const val COLUMN_NAME_YEAR = "year"
        const val COLUMN_NAME_MILEAGE = "mileage"
        const val COLUMN_NAME_TRANSMISSION = "transmission_type"
        const val COLUMN_NAME_DRIVETRAIN = "drivetrain_type"
        const val COLUMN_NAME_DESCRIPTION = "description"
    }
}