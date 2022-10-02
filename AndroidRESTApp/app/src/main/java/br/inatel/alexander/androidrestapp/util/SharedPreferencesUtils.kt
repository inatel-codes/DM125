package br.inatel.alexander.androidrestapp.util

import android.content.Context
import android.content.SharedPreferences
import br.inatel.alexander.androidrestapp.MainApplication
import br.inatel.alexander.androidrestapp.model.User

private const val ACCESS_TOKEN = "access_token"
private const val EXPIRES_IN = "expires_in"
private const val USERNAME = "username"
private const val PASSWORD = "password"

object SharedPreferencesUtils {

    private fun getSharedPreferences() : SharedPreferences {
        val context = MainApplication.getApplicationContext()
        return context.getSharedPreferences("main", Context.MODE_PRIVATE)
    }

    fun saveToken(accessToken: String, expiresIn: Int) {
        with(getSharedPreferences().edit()) {
            putString(ACCESS_TOKEN, accessToken)
            putInt(EXPIRES_IN, (getTimeNow() + expiresIn).toInt())
            commit()
        }
    }

    fun saveUser(user: User) {
        with(getSharedPreferences().edit()) {
            putString(USERNAME, user.email)
            putString(PASSWORD, user.password)
            commit()
        }
    }

    fun getAccessToken(): String? {
        var accessToken: String? = null

        with (getSharedPreferences()) {
            if (contains(ACCESS_TOKEN) && contains(EXPIRES_IN)) {
                val expiresIn = getInt(EXPIRES_IN, 0)

                if (!isTokenExpired(expiresIn)) {
                    accessToken = getString(ACCESS_TOKEN, "")
                }
            }
        }

        return accessToken
    }

    fun getUser(): User? {
        var user = User()

        with (getSharedPreferences()) {
            if (contains(USERNAME) && contains(PASSWORD)) {
                user = User(getString(USERNAME, ""), getString(PASSWORD, ""))
            }
        }

        return user
    }

    private fun getTimeNow(): Long {
        return (System.currentTimeMillis() / 1000)
    }

    private fun isTokenExpired(expiresIn: Int): Boolean {
        return getTimeNow() > expiresIn
    }
}