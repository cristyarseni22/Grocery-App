package com.example.mvpfirstproject.view.adapters.viewHolder

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ItemProductsViewBinding
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.data.ProductsData.Companion.KEY_PRODUCT
import com.example.mvpfirstproject.view.activities.ProductDetailActivity
import com.squareup.picasso.Picasso

class ProductsViewHolder(val binding: ItemProductsViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(product: ProductsData) {
        binding.tvProductName.text = product.productName
        binding.tvProductPrice.text = "$" + product.price.toString()
        binding.tvDescription.text = product.description

        val url = "${Constants.IMAGE_URL}${product.image}"
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

//        itemView.setOnClickListener {
//            val intent = Intent(binding.root.context, ProductDetailActivity::class.java)
//            intent.putExtra(KEY_PRODUCT, product)
//            binding.root.context.startActivity(intent)
//        }

        binding.btnAddToCard.setOnClickListener {
            binding.btnAddToCard.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE
            binding.btnAddToCardAdd.visibility = View.VISIBLE
            product.quantity = 0
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.ivAdd.setOnClickListener {
            product.quantity++
            productDao.updateProduct(product, product.quantity)
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.ivMinus.setOnClickListener {
            product.quantity--
            if (product.quantity < 0) {
                binding.btnAddToCard.visibility = View.VISIBLE
                binding.linearLayout.visibility = View.GONE
                binding.btnAddToCardAdd.visibility = View.GONE
            }
            productDao.updateProduct(product, product.quantity)
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.btnAddToCardAdd.setOnClickListener {

            if (product.quantity == 0) {
                Toast.makeText(
                    binding.root.context,
                    "Failed to add to cart, pleas try again!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    binding.root.context,
                    "${product.quantity} items were added to cart",
                    Toast.LENGTH_SHORT
                ).show()
                productDao.addProduct(product)
            }
        }
    }
}