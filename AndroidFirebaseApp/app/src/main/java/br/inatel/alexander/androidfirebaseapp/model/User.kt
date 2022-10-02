package br.inatel.alexander.androidfirebaseapp.model

import android.net.Uri

data class User(
    var name: String? = null,
    var email: String? = null,
    var avatar: Uri? = null
)