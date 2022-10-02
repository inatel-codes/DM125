package br.inatel.alexander.androidrestapp.product.adapter

import br.inatel.alexander.androidrestapp.model.Product

class ProductClickListener(val clickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) = clickListener(product)
}