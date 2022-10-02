package br.inatel.alexander.androidrestapp.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.inatel.alexander.androidrestapp.databinding.ItemProductBinding
import br.inatel.alexander.androidrestapp.model.Product

class ProductAdapter(private val onProductClickListener: ProductClickListener): ListAdapter<Product, ProductViewHolder>(ProductDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onProductClickListener.onClick(product)
        }
    }

}
