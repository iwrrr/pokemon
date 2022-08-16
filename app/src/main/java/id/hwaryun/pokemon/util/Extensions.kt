package id.hwaryun.pokemon.util

import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

object Extensions {

    fun ShapeableImageView.loadImage(image: String?) {
        Glide.with(this.context)
            .load(image)
            .into(this)
    }

    fun ShapeableImageView.loadImage(uri: Uri) {
        Glide.with(this.context)
            .load(uri)
            .into(this)
    }

    fun ShapeableImageView.loadImage(bitmap: Bitmap) {
        Glide.with(this.context)
            .load(bitmap)
            .into(this)
    }
}