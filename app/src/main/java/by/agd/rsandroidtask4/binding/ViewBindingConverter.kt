package by.agd.rsandroidtask4.binding

import android.net.Uri
import androidx.core.net.toFile
import androidx.databinding.InverseMethod
import java.text.DecimalFormat

object ViewBindingConverter {

    @JvmStatic
    @InverseMethod("floatFromString")
    fun floatToString(value: Float): String {
        return value.toString()
    }

    @JvmStatic
    fun floatFromString(value: String): Float {
        return value.toFloatOrNull() ?: 0f
    }

    @JvmStatic
    @InverseMethod("intFromString")
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    fun intFromString(value: String): Int {
        return value.toIntOrNull() ?: 0
    }

    @JvmStatic
    fun uriToFilename(uri: Uri?): String {
        if (uri == null) return ""
        return uri.encodedPath.toString()
    }

    @JvmStatic
    fun decimalFormat(value: Any, pattern: String): String {
        return DecimalFormat(pattern).format(value)
    }
}