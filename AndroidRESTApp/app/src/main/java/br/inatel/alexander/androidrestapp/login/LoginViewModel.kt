package br.inatel.alexander.androidrestapp.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidrestapp.model.OauthTokenResponse
import br.inatel.alexander.androidrestapp.model.User
import br.inatel.alexander.androidrestapp.network.*
import br.inatel.alexander.androidrestapp.util.SharedPreferencesUtils
import kotlinx.coroutines.*

private const val TAG = "LoginViewModel"

private const val BASIC_TOKEN = "Basic c2llY29sYTptYXRpbGRl"
private const val GRANT_TYPE = "password"

class LoginViewModel(): ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var user: MutableLiveData<User> = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun login(): Boolean {
        return try {
            val response = retrieveNewToken(user.value!!.email!!, user.value!!.password!!)
            SharedPreferencesUtils.saveToken(response.accessToken, response.expiresIn)
            SharedPreferencesUtils.saveUser(user.value!!)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun retrieveNewToken(username: String, password: String): OauthTokenResponse {
        Log.i(TAG, "Retrieving new token")

        return SalesApi.retrofitService.getToken(
            BASIC_TOKEN,
            GRANT_TYPE,
            username,
            password
        ).execute().body()!!
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
    }
}