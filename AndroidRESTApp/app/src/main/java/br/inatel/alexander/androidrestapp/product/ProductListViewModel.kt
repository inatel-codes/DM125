package br.inatel.alexander.androidrestapp.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidrestapp.network.SalesApi
import br.inatel.alexander.androidrestapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

class ProductListViewModel: ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")

        coroutineScope.launch {
            Log.i(TAG, "Loading products")
            val products = SalesApi.retrofitService.getProductsAsync().await()
            Log.i(TAG, "Number of products ${products.size}")
            _products.value = products
        }

        Log.i(TAG, "Products list requested")
    }

    fun refreshProducts() {
        getProducts()
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
        viewModelJob.cancel()
    }
}