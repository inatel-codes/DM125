package com.example.androidproject04.productdetail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidproject04.R
import com.example.androidproject04.databinding.FragmentProductDetailBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

private const val TAG = "ProductDetailFragment"

class ProductDetailFragment: Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private var productCode: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating ProductDetailFragment")
        binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // Fetch the product code and create the ViewModel here
        productCode = ProductDetailFragmentArgs.fromBundle(requireArguments()).productCode
        val productDetailViewModelFactory = ProductDetailViewModelFactory(productCode)
        binding.productDetailViewModel = ViewModelProvider(this, productDetailViewModelFactory)[ProductDetailViewModel::class.java]

        val remoteConfig = Firebase.remoteConfig
        setHasOptionsMenu(remoteConfig.getBoolean("delete_detail_view"))

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_product -> {
                binding.productDetailViewModel?.deleteProduct()

                val firebaseAnalytics = FirebaseAnalytics.getInstance(this.requireContext())
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, productCode)
                firebaseAnalytics.logEvent("delete_item", bundle)

                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}