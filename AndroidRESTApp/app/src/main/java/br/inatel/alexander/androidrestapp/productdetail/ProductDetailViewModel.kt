package br.inatel.alexander.androidrestapp.productdetail

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

private const val TAG = "ProductDetailViewModel"

class ProductDetailViewModel(private val code: String): ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    init {
        getProduct()
    }

    private fun getProduct() {
        Log.i(TAG, "Preparing to request a product by its code")

        coroutineScope.launch {
            Log.i(TAG, "Fetching product by its code")
            val product = SalesApi.retrofitService.getProductByCodeAsync(code).await()
            Log.i(TAG, "Name of the product ${product.name}")
            _product.value = product
        }

        Log.i(TAG, "Product requested by code")
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
        viewModelJob.cancel()
    }
}