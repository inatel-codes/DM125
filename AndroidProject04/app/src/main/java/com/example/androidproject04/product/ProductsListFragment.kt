package com.example.androidproject04.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.androidproject04.databinding.FragmentProductsListBinding
import com.google.firebase.analytics.FirebaseAnalytics

private const val TAG = "ProductsListFragment"

class ProductsListFragment: Fragment() {
    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProductsListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.productListViewModel = productListViewModel

        var itemDecor = DividerItemDecoration(context, VERTICAL)
        binding.rcvProducts.addItemDecoration(itemDecor)

        binding.rcvProducts.adapter = ProductAdapter(ProductAdapter.ProductClickListener { product ->
            Log.i(TAG, "Product selected ${product.name}")
            this.findNavController().navigate(ProductsListFragmentDirections.actionShowProductDetail(product.code!!))
        })

        binding.fab.setOnClickListener { view ->
            val firebaseAnalytics = FirebaseAnalytics.getInstance(this.requireContext())
            firebaseAnalytics.logEvent("new_item", null)

            this.findNavController()
                .navigate(ProductsListFragmentDirections.actionShowProductDetail(null))
        }

        return binding.root
    }
}