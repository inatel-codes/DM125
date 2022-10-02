package br.inatel.alexander.androidpushnotificationapp.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.inatel.alexander.androidpushnotificationapp.model.Product

class ProductInfoViewModel: ViewModel() {
    val fcmRegistrationId = MutableLiveData<String>()
    val product = MutableLiveData<Product>()
}