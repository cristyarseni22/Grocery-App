package com.example.mvpfirstproject.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.databinding.ActivityAddressBinding
import com.example.mvpfirstproject.model.remote.Constants.ADDRESS_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.Constants.USER_ID
import com.example.mvpfirstproject.model.remote.data.AddressData
import com.example.mvpfirstproject.model.remote.data.AddressResponse
import com.example.mvpfirstproject.view.adapters.AddressAdapter
import com.google.gson.Gson

class AddressActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddressBinding
    private var addressList: List<AddressData> = ArrayList()
    lateinit var adapter: AddressAdapter
    private var userId: String? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        adapter = AddressAdapter(addressList)
        binding.rvAddress.layoutManager = LinearLayoutManager(baseContext)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, CartActivity::class.java))
        }

        binding.rvAddress.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        binding.btnAddNewAddress.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }
        adapter.notifyDataSetChanged()

        val pref = getSharedPreferences(LOGIN_DETAILS, MODE_PRIVATE)
        userId = pref.getString(USER_ID, null).toString()
    }

    private fun getData() {

        val url = "${BASE_URL}${ADDRESS_END_POINT}"

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressList = addressResponse.addresses
                adapter = AddressAdapter(addressList)

                binding.rvAddress.adapter = adapter
            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }
}