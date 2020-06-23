package by.agd.rsandroidtask4.model

import android.net.Uri

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
)
