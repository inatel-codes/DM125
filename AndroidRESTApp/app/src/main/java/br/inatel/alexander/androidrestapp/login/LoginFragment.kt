package br.inatel.alexander.androidrestapp.login

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.inatel.alexander.androidrestapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

private const val TAG = "LoginFragment"

class LoginFragment: Fragment() {
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating LoginFragment")

        var binding = FragmentLoginBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.loginViewModel = loginViewModel

        binding.btnLogin.setOnClickListener {
            Thread {
                val success = binding.loginViewModel?.login()
                activity?.runOnUiThread {
                    if (success == true) {
                        this.findNavController().navigate(LoginFragmentDirections.actionShowProductList())
                    } else {
                        Toast.makeText(activity, "Login failed! Email or password incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }.start()
        }

        return binding.root
    }

}