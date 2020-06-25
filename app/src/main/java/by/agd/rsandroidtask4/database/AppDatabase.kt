package by.agd.rsandroidtask4.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class CarDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CarContract.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(CarContract.SQL_DELETE_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_NAME = "car_database.db"
        const val DATABASE_VERSION = 2

        private var INSTANCE: CarDatabase? = null

        fun init(context: Context) {
            INSTANCE = CarDatabase(context)
        }

        fun getInstance(): CarDatabase? {
            return INSTANCE
        }
    }
}

