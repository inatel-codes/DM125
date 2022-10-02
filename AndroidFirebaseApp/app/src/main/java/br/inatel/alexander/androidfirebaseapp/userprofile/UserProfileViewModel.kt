package br.inatel.alexander.androidfirebaseapp.userprofile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidfirebaseapp.model.User
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "UserProfileViewModel"

class UserProfileViewModel(): ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData<User>()

    init {
        user.value = User()
        getUser()
    }

    private fun getUser() {
        Log.i(TAG, "Setting user info")
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            user.value?.name = firebaseUser.displayName
            user.value?.email = firebaseUser.email
            user.value?.avatar = firebaseUser.photoUrl
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
    }
}