package br.inatel.alexander.androidfirebaseapp.persistence

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.inatel.alexander.androidfirebaseapp.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "ProductRepository"

private const val COLLECTION = "products"
private const val FIELD_USER_ID = "userId"
private const val FIELD_NAME = "name"
private const val FIELD_DESCRIPTION = "description"
private const val FIELD_CODE = "code"
private const val FIELD_PRICE = "price"

object ProductRepository {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun saveProduct(product: Product): String {
        val document = if (product.id != null) {
            firebaseFirestore
                .collection(COLLECTION)
                .document(product.id!!)
        } else {
            product.userId = firebaseAuth.currentUser!!.uid
            firebaseFirestore
                .collection(COLLECTION)
                .document()
        }

        document.set(product)

        return document.id
    }

    fun deleteProduct(productId: String) {
        val document = firebaseFirestore
            .collection(COLLECTION)
            .document(productId)
        document.delete()
    }

    fun getProducts(): MutableLiveData<List<Product>> {
        val liveProducts = MutableLiveData<List<Product>>()

        firebaseFirestore
            .collection(COLLECTION)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.currentUser!!.uid)
            .orderBy(FIELD_NAME, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }

                val products = ArrayList<Product>()

                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    querySnapshot.forEach { queryDoc ->
                        val product = queryDoc.toObject<Product>()
                        product.id = queryDoc.id
                        products.add(product)
                    }
                } else {
                    Log.d(TAG, "No product has been found")
                }

                liveProducts.postValue(products)
            }

        return liveProducts
    }

    fun getProductByCode(code: String): MutableLiveData<Product> {
        val liveProduct: MutableLiveData<Product> = MutableLiveData()

        firebaseFirestore
            .collection(COLLECTION)
            .whereEqualTo(FIELD_CODE, code)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.currentUser!!.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }

                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val products = ArrayList<Product>()
                    querySnapshot.forEach {
                        val product = it.toObject<Product>()
                        product.id = it.id
                        products.add(product)
                    }
                    liveProduct.postValue(products[0])
                } else {
                    Log.d(TAG, "No product has been found")
                }
            }

        return liveProduct
    }
}