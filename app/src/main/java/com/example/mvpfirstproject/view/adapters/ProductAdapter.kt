package com.example.mvpfirstproject.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.databinding.ItemProductsViewBinding
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.view.adapters.viewHolder.ProductsViewHolder

class ProductAdapter(var products: List<ProductsData>) :
    RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductsViewBinding.inflate(layoutInflater, parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = products[position]
        holder.setData(products)

        holder.itemView.setOnClickListener {
            if (this::optionSelectedListener.isInitialized) {
                optionSelectedListener(products, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private lateinit var optionSelectedListener: (ProductsData, Int) -> Unit

    fun setOnOptionSelectedListener(listener: (ProductsData, Int) -> Unit) {
        this.optionSelectedListener = listener
    }
}