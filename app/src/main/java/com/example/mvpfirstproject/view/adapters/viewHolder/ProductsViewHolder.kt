package com.example.mvpfirstproject.view.adapters.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ItemProductsViewBinding
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.Constants.IMAGE_URL
import com.squareup.picasso.Picasso

class ProductsViewHolder(val binding: ItemProductsViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(product: ProductsData) {
        binding.tvProductName.text = product.productName
        binding.tvProductPrice.text = "$" + product.price
        binding.tvDescription.text = product.description

        val url = "${IMAGE_URL}${product.image}"
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.noimage)
            .into(binding.ivProductPhoto)

        val productDao = ProductDao(binding.root.context)

        val qty = binding.tvQtyu.text.toString().toInt()

        if (qty == 0) {
            binding.linearLayout.visibility = View.GONE
        } else {
            binding.btnAddToCard.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE
        }

        binding.btnAddToCard.setOnClickListener {
            binding.btnAddToCard.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE
            binding.btnAddToCardAdd.visibility = View.VISIBLE
            product.quantity = 0
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.ivAdd.setOnClickListener {
            product.quantity++
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.ivMinus.setOnClickListener {
            product.quantity--
            if (product.quantity < 0) {
                binding.btnAddToCard.visibility = View.VISIBLE
                binding.linearLayout.visibility = View.GONE
                binding.btnAddToCardAdd.visibility = View.GONE
            }
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.btnAddToCardAdd.setOnClickListener {
            if (product.quantity != 0) {
                productDao.addProduct(product)
            } else {
            }
        }
    }
}