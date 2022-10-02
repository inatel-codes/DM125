package br.inatel.alexander.androidfirebaseapp.util

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import br.inatel.alexander.androidfirebaseapp.R
import java.net.URL

object ImageUtil {
    fun getBitmapFromImageUrl(imageUrl: String, crop: Boolean): Bitmap? {
        val url = URL(imageUrl)
        val imageBitmap: Bitmap = BitmapFactory.decodeStream(url.openStream())

        if (crop) {
            return CircleTransform().transform(imageBitmap)
        }

        return imageBitmap
    }
}