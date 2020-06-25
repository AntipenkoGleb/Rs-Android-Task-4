package by.agd.rsandroidtask4.binding

import java.io.Serializable

data class ImageListItem<out A, out B>(val first: A, val second: B) :
    Serializable {
    override fun toString(): String = first.toString()
}