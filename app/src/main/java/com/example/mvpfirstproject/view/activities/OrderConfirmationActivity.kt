package com.example.mvpfirstproject.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.databinding.ActivityOrderConfirmationBinding
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.ORDER_END_POINT
import com.example.mvpfirstproject.model.remote.data.AddressData
import com.example.mvpfirstproject.model.remote.data.ProductsData
import org.json.JSONObject

class OrderConfirmationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderConfirmationBinding
    lateinit var dao: ProductDao
    lateinit var address: AddressData
    lateinit var list: List<ProductsData>
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)

        binding.buttonGoHome.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            dao.deleteTable()

        }

        val intent = Intent(this, LoginActivity::class.java)
        binding.buttonLogout.setOnClickListener {
            startActivity(intent)
        }

        placeOrder()
    }

    private fun placeOrder() {
        val userId = "6392b90378190700110dbc9c"

        list = dao.getProducts()

        val url = "${BASE_URL}${ORDER_END_POINT}"

        val product: ArrayList<Any> = ArrayList()
        val oneProduct = HashMap<String, Any>()

        for (item in list) {
            oneProduct["price"] = item.price.toString()
            oneProduct["quantity"] = item.quantity
            oneProduct["productName"] = item.productName.toString()
            val jsonObjectProduct = JSONObject(oneProduct as Map<*, *>)
            product.add(jsonObjectProduct)
        }

        val shipAddress = HashMap<String, Any?>()
        shipAddress["title"] = address.title
        shipAddress["address"] = address.address
        val jsonAddress = JSONObject(shipAddress as Map<*, *>)

        val params = HashMap<String, Any>()
        params["userId"] = userId
        params["products"] = product
        params["shippingAddress"] = jsonAddress

        val jsonObject = JSONObject(params as Map<*, *>)
        val requestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(
            Request.Method.POST, url,
            jsonObject,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")

                if (status == 0) {
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                }
            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(request)
    }
}