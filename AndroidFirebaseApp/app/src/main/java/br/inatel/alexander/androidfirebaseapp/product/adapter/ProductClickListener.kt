package br.inatel.alexander.androidfirebaseapp.product.adapter

import br.inatel.alexander.androidfirebaseapp.model.Product

class ProductClickListener(val clickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) = clickListener(product)
}