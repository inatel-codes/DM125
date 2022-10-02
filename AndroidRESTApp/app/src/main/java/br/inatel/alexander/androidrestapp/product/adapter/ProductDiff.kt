package br.inatel.alexander.androidrestapp.product.adapter

import androidx.recyclerview.widget.DiffUtil
import br.inatel.alexander.androidrestapp.model.Product

object ProductDiff: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return ((oldItem.id == newItem.id)
                && (oldItem.name == newItem.name)
                && (oldItem.code == newItem.code)
                && (oldItem.price == newItem.price))
    }
}