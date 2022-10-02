package br.inatel.alexander.androidfirebaseapp.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidfirebaseapp.model.Product
import br.inatel.alexander.androidfirebaseapp.persistence.ProductRepository

private const val TAG = "ProductListViewModel"

class ProductListViewModel: ViewModel() {
    private lateinit var _products: MutableLiveData<List<Product>>
    val products: LiveData<List<Product>> get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")

        _products = ProductRepository.getProducts()

        Log.i(TAG, "Products list requested")
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
    }
}