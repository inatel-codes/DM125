package br.inatel.alexander.androidfirebaseapp.productdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidfirebaseapp.model.Product
import br.inatel.alexander.androidfirebaseapp.persistence.ProductRepository

private const val TAG = "ProductDetailViewModel"

class ProductDetailViewModel(private val code: String?): ViewModel() {
    lateinit var product: MutableLiveData<Product>

    init {
        if (code != null) {
            getProduct(code)
        } else {
            product = MutableLiveData<Product>()
            product.value = Product()
        }
    }

    private fun getProduct(productCode: String) {
        Log.i(TAG, "Preparing to request a product by its code")

        product = ProductRepository.getProductByCode(productCode)

        Log.i(TAG, "Product requested by code")
    }

    fun saveProduct() {
        Log.i(TAG, "Saving product " + (product.value?.name ?: "NOT FOUND"))

        if (product.value != null && product.value!!.code != null && product.value!!.code!!.isNotBlank()) {
            ProductRepository.saveProduct(product.value!!)
        }
    }

    fun deleteProduct() {
        Log.i(TAG, "Deleting product " + (product.value?.name ?: "NOT FOUND"))

        if (product.value?.id != null) {
            ProductRepository.deleteProduct(product.value!!.id!!)
            product.value = null
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
    }
}