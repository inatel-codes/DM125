package br.inatel.alexander.androidfirebaseapp.product.adapter

import androidx.recyclerview.widget.RecyclerView
import br.inatel.alexander.androidfirebaseapp.databinding.ItemProductBinding
import br.inatel.alexander.androidfirebaseapp.model.Product

class ProductViewHolder(private var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}