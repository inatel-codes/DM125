package br.inatel.alexander.androidrestapp.product.adapter

import androidx.recyclerview.widget.RecyclerView
import br.inatel.alexander.androidrestapp.databinding.ItemProductBinding
import br.inatel.alexander.androidrestapp.model.Product

class ProductViewHolder(private var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}