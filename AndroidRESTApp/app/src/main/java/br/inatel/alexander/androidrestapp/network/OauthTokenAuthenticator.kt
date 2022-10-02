package br.inatel.alexander.androidrestapp.network

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.util.Log
import androidx.core.app.NavUtils
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import br.inatel.alexander.androidrestapp.MainActivity
import br.inatel.alexander.androidrestapp.R
import br.inatel.alexander.androidrestapp.model.OauthTokenResponse
import br.inatel.alexander.androidrestapp.util.SharedPreferencesUtils
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

private const val TAG = "OauthTokenAuthenticator"

private const val BASIC_TOKEN = "Basic c2llY29sYTptYXRpbGRl"
private const val USERNAME = "alex@mail.com"
private const val PASSWORD = "123"
private const val GRANT_TYPE = "password"

class OauthTokenAuthenticator(): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = retrieveNewToken()
        SharedPreferencesUtils.saveToken(token.accessToken, token.expiresIn)
        return response.request().newBuilder()
            .header("Authorization", "Bearer ${token.accessToken}")
            .build()
    }

    private fun retrieveNewToken(): OauthTokenResponse {
        Log.i(TAG, "Retrieving new token")

        return SalesApi.retrofitService.getToken(
            BASIC_TOKEN,
            GRANT_TYPE,
            SharedPreferencesUtils.getUser()!!.email!!,
            SharedPreferencesUtils.getUser()!!.password!!
        ).execute().body()!!
    }
}