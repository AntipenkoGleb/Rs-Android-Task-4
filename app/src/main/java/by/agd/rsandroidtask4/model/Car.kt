package by.agd.rsandroidtask4.model

import android.net.Uri

data class Car(
    var id: Long = 0,
    var carMaker: Int = 0,
    var model: String = "",
    var price: Float = 0f,
    var imageUri: Uri? = null,
    var body: Int = 0,
    var color: Int = 0,
    var year: Int = 0,
    var mileage: Int = 0,
    var transmission: Int = 0,
    var drivetrain: Int = 0,
    var description: String = ""
)
