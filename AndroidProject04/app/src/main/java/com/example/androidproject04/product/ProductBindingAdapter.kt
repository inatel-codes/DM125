package com.example.androidproject04.product

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject04.persistence.Product
import java.text.DecimalFormat

@BindingAdapter("productsList")
fun bindProductsList(recyclerView: RecyclerView, products: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(products)
}

@BindingAdapter("productPrice")
fun bindProductPrice(txtProductPrice: TextView, productPrice: Double?) {
    /* if (productPrice != null) {
        txtProductPrice.text = "$ %.2f".format(productPrice)
    } */
    txtProductPrice.text = productPrice?.let { price -> "$ $price" }
}