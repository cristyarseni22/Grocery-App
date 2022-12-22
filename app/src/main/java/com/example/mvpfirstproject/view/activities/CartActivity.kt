package com.example.mvpfirstproject.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ActivityCartBinding
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.view.adapters.CartAdapter

class CartActivity : AppCompatActivity(), CartAdapter.OnAdapterInteraction {

    lateinit var binding: ActivityCartBinding
    lateinit var products: MutableList<ProductsData>
    lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, SubcategoryActivity::class.java))
        }

        val productDao = ProductDao(baseContext)
        products = productDao.getProducts()

        binding.layoutEmptyCart.visibility = View.GONE

        adapter = CartAdapter(this, products)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setAdapterListener(this)

        calculateTotals()

        if (products.size == 0) {
            binding.layoutFullCart.visibility = View.GONE
            binding.layoutEmptyCart.visibility = View.VISIBLE
            binding.buttonGoShopping.setOnClickListener {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }

        binding.buttonAddress.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
    }

    private fun calculateTotals() {
        val delivery: Double
        var orderAmount = 0.0

        for (product: ProductsData in products) {
            orderAmount += (product.price * product.quantity)
        }

        delivery = if (orderAmount > 150) {
            0.00
        } else if (orderAmount == 0.0) {
            0.0
        } else {
            25.0
        }

        orderAmount += delivery

        binding.textViewDeliveryAmount.text = "$ $delivery"
        binding.textViewTotalAmount.text = "$ $orderAmount"
    }

    private fun isCartEmpty(): Boolean {
        var isEmpty = false
        if (products.size == 0) {

            binding.layoutFullCart.visibility = View.GONE
            binding.layoutEmptyCart.visibility = View.VISIBLE
            binding.buttonGoShopping.setOnClickListener {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            isEmpty = true
        }
        return isEmpty
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClickedItemListener(view: View, position: Int, id: String) {
        val dao = ProductDao(baseContext)

        when (view.id) {
            R.id.button_delete -> {
                dao.deleteProduct(id)
                products.removeAt(position)
                adapter.notifyDataSetChanged()
                calculateTotals()
                isCartEmpty()
            }
        }
    }
}


