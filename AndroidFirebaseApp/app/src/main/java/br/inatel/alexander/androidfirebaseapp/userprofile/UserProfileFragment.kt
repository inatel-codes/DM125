package br.inatel.alexander.androidfirebaseapp.userprofile

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.inatel.alexander.androidfirebaseapp.databinding.FragmentUserProfileBinding

private const val TAG = "UserProfileFragment"

class UserProfileFragment: Fragment() {
    private val userProfileViewModel: UserProfileViewModel by lazy {
        ViewModelProvider(this)[UserProfileViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating UserProfileFragment")

        val binding = FragmentUserProfileBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.userProfileViewModel = userProfileViewModel

        return binding.root
    }

}