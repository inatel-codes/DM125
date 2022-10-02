package br.inatel.alexander.androidpushnotificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import br.inatel.alexander.androidpushnotificationapp.product.ProductInfoFragmentDirections

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent.getStringExtra("product")?.let { productInfo ->
            showProductInfo(productInfo)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        intent?.getStringExtra("product")?.let { productInfo ->
            showProductInfo(productInfo)
        }
        super.onNewIntent(intent)
    }

    private fun showProductInfo(productInfo: String) {
        this.findNavController(R.id.nav_host_fragment)
            .navigate(ProductInfoFragmentDirections.actionShowProductInfo(productInfo))
    }
}