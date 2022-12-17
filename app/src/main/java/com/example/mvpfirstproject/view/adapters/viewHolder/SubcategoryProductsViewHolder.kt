package com.example.mvpfirstproject.view.adapters.viewHolder

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.databinding.ItemSubcategoryProductsViewBinding
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.PRODUCTS_END_POINT
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.model.remote.data.ProductsData.Companion.KEY_PRODUCT
import com.example.mvpfirstproject.model.remote.data.ProductsResponse
import com.example.mvpfirstproject.model.remote.data.SubcategoryData
import com.example.mvpfirstproject.view.activities.ProductDetailActivity
import com.example.mvpfirstproject.view.adapters.ProductAdapter
import com.google.gson.Gson

class SubcategoryProductsViewHolder(
    val binding: ItemSubcategoryProductsViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val queue = Volley.newRequestQueue(binding.root.context)
    fun bind(subcategory: SubcategoryData) {

        val id = subcategory.subId

        val url = "${BASE_URL}${PRODUCTS_END_POINT}$id"

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductsResponse::class.java)

                val adapter = ProductAdapter(response.data)
                binding.rvProducts.layoutManager = LinearLayoutManager(binding.root.context)

                adapter.setOnOptionSelectedListener { product, _ ->
                    val intent = Intent(binding.root.context, ProductDetailActivity::class.java)
                    intent.putExtra(KEY_PRODUCT, product._id)
                    binding.root.context.startActivity(intent)
                }

                binding.rvProducts.adapter = adapter

            },
            {
                it.printStackTrace()
                Toast.makeText(binding.root.context, "Error is: $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }

}

