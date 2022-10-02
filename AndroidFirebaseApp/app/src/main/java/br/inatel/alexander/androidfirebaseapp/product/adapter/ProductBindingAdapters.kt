package br.inatel.alexander.androidfirebaseapp.product.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.inatel.alexander.androidfirebaseapp.model.Product

@BindingAdapter("products")
fun bindProducts(recyclerView: RecyclerView, products: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(products)
}

@BindingAdapter("productPrice")
fun bindProductPrice(txtProductPrice: TextView, productPrice: Double?) {
    txtProductPrice.text = productPrice?.let {"$ " + "%.2f".format(it)}
}
