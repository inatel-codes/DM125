package br.inatel.alexander.androidfirebaseapp.product

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
import br.inatel.alexander.androidfirebaseapp.databinding.FragmentProductsListBinding
import br.inatel.alexander.androidfirebaseapp.product.adapter.ProductAdapter
import br.inatel.alexander.androidfirebaseapp.product.adapter.ProductClickListener

private const val TAG = "ProductListFragment"

class ProductListFragment: Fragment() {
    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating ProductsListFragment")

        val binding = FragmentProductsListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.productListViewModel = productListViewModel

        val itemDecor = DividerItemDecoration(context, VERTICAL)
        binding.rcvProducts.addItemDecoration(itemDecor)

        binding.rcvProducts.adapter = ProductAdapter(ProductClickListener { product ->
            Log.i(TAG,"Product selected: ${product.name}")
            this.findNavController().navigate(ProductListFragmentDirections.actionShowProductDetail(product.code!!))
        })

        binding.fab.setOnClickListener {
            this.findNavController()
                .navigate(ProductListFragmentDirections.actionShowProductDetail(null))
        }

        return binding.root
    }
}