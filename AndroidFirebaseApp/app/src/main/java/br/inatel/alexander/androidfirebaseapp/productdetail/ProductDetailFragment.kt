package br.inatel.alexander.androidfirebaseapp.productdetail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.inatel.alexander.androidfirebaseapp.R
import br.inatel.alexander.androidfirebaseapp.databinding.FragmentProductDetailBinding

private const val TAG = "ProductDetailFragment"

class ProductDetailFragment: Fragment() {
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating ProductDetailFragment")

        binding = FragmentProductDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val productCode = ProductDetailFragmentArgs.fromBundle(requireArguments()).productCode
        val productDetailViewModelFactory = ProductDetailViewModelFactory(productCode)
        binding.productDetailViewModel = ViewModelProvider(
            this,
            productDetailViewModelFactory
        )[ProductDetailViewModel::class.java]

        binding.btnSave.setOnClickListener {
            binding.productDetailViewModel?.saveProduct()
            findNavController().popBackStack()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_product -> {
                binding.productDetailViewModel?.deleteProduct()
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}