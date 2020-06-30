package by.agd.rsandroidtask4.binding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load


@BindingAdapter("loadImage", "loadImagePlaceholder", "loadImageError", requireAll = false)
fun setImageUri(view: ImageView, uri: Uri, placeholderImage: Drawable?, errorImage: Drawable?) {
    view.load(uri) {
        placeholderImage?.let { placeholder(it) }
        errorImage?.let { error(it) }
    }
}




