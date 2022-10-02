package br.inatel.alexander.androidrestapp.product

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.inatel.alexander.androidrestapp.R
import br.inatel.alexander.androidrestapp.databinding.FragmentProductsListBinding
import br.inatel.alexander.androidrestapp.product.adapter.ProductAdapter
import br.inatel.alexander.androidrestapp.product.adapter.ProductClickListener

private const val TAG = "ProductListFragment"

class ProductListFragment: Fragment() {
    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "Creating ProductsListFragment")

        val binding = FragmentProductsListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.productListViewModel = productListViewModel

        val itemDecor = DividerItemDecoration(context, VERTICAL)
        binding.rcvProducts.addItemDecoration(itemDecor)

        binding.rcvProducts.adapter = ProductAdapter(ProductClickListener { product ->
            Log.i(TAG, "Product selected: ${product.name}")
            this.findNavController()
                .navigate(ProductListFragmentDirections.actionShowProductDetail(product.code))
        })

        binding.productsRefresh.setOnRefreshListener {
            Log.i(TAG, "Refreshing products list")
            productListViewModel.refreshProducts()
            binding.productsRefresh.isRefreshing = false
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                this.findNavController().navigate(R.id.fragmentLogin)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}