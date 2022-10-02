package br.inatel.alexander.androidfirebaseapp

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.inatel.alexander.androidfirebaseapp.product.ProductListFragmentDirections
import br.inatel.alexander.androidfirebaseapp.util.ImageUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeApp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        mainMenu = menu
        updateUserPicture()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
                navController.navigate(R.id.fragmentUserProfile);
                true
            }
            R.id.logout -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        this.recreate()
                    }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initializeApp() {
        FirebaseApp.initializeApp(this)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            updateUserPicture()
            setContentView(R.layout.activity_main)
        } else {
            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            updateUserPicture()
            setContentView(R.layout.activity_main)
        } else {
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserPicture() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && mainMenu != null) {
            Thread {
                val image: Bitmap? = ImageUtil.getBitmapFromImageUrl(user.photoUrl.toString(), true)
                runOnUiThread {
                    val bd = BitmapDrawable(resources, image)
                    mainMenu!!.findItem(R.id.user_account).icon = bd
                }
            }.start()
        }
    }
}