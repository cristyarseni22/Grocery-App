package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ActivityProductDetailBinding
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.IMAGE_URL
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.model.remote.data.ProductsData.Companion.KEY_PRODUCT
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.Constants.PRODUCT_DETAILS_EP
import com.example.mvpfirstproject.model.remote.data.ProductDetails.Companion.KEY_PRODUCT_DETAILS
import com.example.mvpfirstproject.model.remote.data.ProductDetailsResponse
import com.example.mvpfirstproject.model.remote.data.ProductsResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    private lateinit var queue: RequestQueue
    private lateinit var dao: ProductDao
    private lateinit var product: ProductsData
    private var productId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.apply {
            ivBack.setOnClickListener {
                startActivity(Intent(baseContext, SubcategoryActivity::class.java))
            }
            ivGoToCart.setOnClickListener {
                startActivity(Intent(baseContext, CartActivity::class.java))
            }
        }

        product = ProductsData()

        dao = ProductDao(baseContext)

//        binding.btnAddToCard.setOnClickListener {
//            binding.btnAddToCard.visibility = View.GONE
//            binding.linearLayout.visibility = View.VISIBLE
//            binding.btnAddToCardAdd.visibility = View.VISIBLE
//        }
//
//        binding.ivAdd.setOnClickListener {
//            product.quantity++
//            dao.updateProduct(product, product.quantity)
//            binding.tvQtyu.text = product.quantity.toString()
//        }
//
//        binding.ivMinus.setOnClickListener {
//            product.quantity--
//            dao.updateProduct(product, product.quantity)
//            binding.tvQtyu.text = product.quantity.toString()
//        }
//
//        binding.btnAddToCardAdd.setOnClickListener {
//            val x = binding.tvQtyu.text.toString()
//            if (x > 0.toString()) {
//                dao.updateProduct(product, product.quantity)
//            }
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
            dao.updateProduct(product, product.quantity)
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.ivMinus.setOnClickListener {
            product.quantity--
            if (product.quantity < 0) {
                binding.btnAddToCard.visibility = View.VISIBLE
                binding.linearLayout.visibility = View.GONE
                binding.btnAddToCardAdd.visibility = View.GONE
            }
            dao.updateProduct(product, product.quantity)
            binding.tvQtyu.text = product.quantity.toString()
        }

        binding.btnAddToCardAdd.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${product.quantity} products were added to cart",
                Toast.LENGTH_SHORT
            ).show()
            dao.addProduct(product)
        }

        getProductDetails()
    }

    private fun getProductDetails() {
        productId = intent.getStringExtra(KEY_PRODUCT).toString()
        val url = "${BASE_URL}${PRODUCT_DETAILS_EP}$productId"

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductDetailsResponse::class.java)

                val product = response.data
                binding.tvProductNameD.text = product.productName
                binding.tvProductDescriptionD.text = product.description
                binding.priceD.text = "$${product.price}"

                val imageUrl = "${IMAGE_URL}${product.image}"
                Picasso.get().load(imageUrl).placeholder(R.drawable.loading)
                    .error(R.drawable.noimage).into(binding.ivPhotoD)

            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
}