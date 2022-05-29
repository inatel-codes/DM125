package com.example.androidproject02.network

import android.util.Log
import com.example.androidproject02.util.SharedPreferenceUtils
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

private const val TAG = "OauthTokenAuthenticator"
private const val USERNAME = "alex@mail.com"
private const val PASSWORD = "123"
private const val BASIC_TOKEN = "Basic c2llY29sYTptYXRpbGRl"

class OauthTokenAuthenticator(): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = retrieveNewToken()
        SharedPreferenceUtils.saveToken(token.accessToken, token.expiresIn)

        return response.request().newBuilder()
            .header("Authorization", "Bearer ${token.accessToken}")
            .build()
    }

    private fun retrieveNewToken(): OauthTokenResponse {
        Log.i(TAG, "Retrieving new token")
        return SalesApi.retrofitService.getToken(
            BASIC_TOKEN,
        "password",
            USERNAME,
            PASSWORD).execute().body()!!
    }
}