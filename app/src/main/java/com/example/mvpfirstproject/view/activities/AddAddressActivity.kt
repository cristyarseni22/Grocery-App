package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.databinding.ActivityAddAddressBinding
import com.example.mvpfirstproject.model.remote.Constants.ADDRESS_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.ADD_ADDRESS_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import org.json.JSONObject

class AddAddressActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddAddressBinding
    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, AddressActivity::class.java))
        }

        binding.btnSaveAddress.setOnClickListener {
            addAddress()
        }
    }

    private fun addAddress() {
        val url = "${BASE_URL}${ADD_ADDRESS_END_POINT}"
        val data = JSONObject()

        val title = binding.edtTitle.text.toString()
        val address = binding.editAddress.text.toString()

        data.put("user_id", 1)
        data.put("title", title)
        data.put("address", address)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")

                if (status == 0) {
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                }
            },
            { _ ->
                Toast.makeText(
                    baseContext,
                    "Failed to add address. Please retry.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
        queue.add(request)
    }
}