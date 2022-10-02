package br.inatel.alexander.androidfirebaseapp.userprofile

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.inatel.alexander.androidfirebaseapp.util.CircleTransform
import com.squareup.picasso.Picasso


@BindingAdapter("android:src")
fun bindImage(imageView: ImageView, userAvatar: Uri) {
    Picasso
        .get()
        .load(userAvatar)
        .transform(CircleTransform())
        .into(imageView)
}