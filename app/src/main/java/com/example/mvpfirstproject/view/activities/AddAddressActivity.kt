package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvpfirstproject.databinding.ActivityAddAddressBinding
import com.example.mvpfirstproject.model.remote.data.Address
import com.example.mvpfirstproject.model.remote.handlers.AddressVolleyHandler
import com.example.mvpfirstproject.presenter.address.AddressMVP
import com.example.mvpfirstproject.presenter.address.AddressPresenter
import com.google.android.material.snackbar.Snackbar

class AddAddressActivity : AppCompatActivity(), AddressMVP.AddressView {
    lateinit var binding: ActivityAddAddressBinding
    lateinit var presenter: AddressMVP.AddressPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, AddressActivity::class.java))
        }

        binding.btnSaveAddress.setOnClickListener {
            setUpEvents()
        }
    }

    private fun setUpEvents() {

        val addressVolleyHandler = AddressVolleyHandler(this)
        presenter = AddressPresenter(addressVolleyHandler, this)
        binding.apply {


            val city = edtCity.text.toString()
            val street = edtStreetName.text.toString()
            val zipCode = edtZipCode.text.toString()
            val houseType = edtType.text.toString()
            val houseNo = edtHouseNo.text.toString()


            if (city.isEmpty()) {
                Toast.makeText(baseContext, "City field cannot be empty", Toast.LENGTH_SHORT).show()
            } else if (street.isEmpty()) {
                Toast.makeText(baseContext, "Street field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else if (zipCode.isEmpty()) {
                Toast.makeText(baseContext, "Zip code field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else if (houseType.isEmpty()) {
                Toast.makeText(baseContext, "House type field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
             } else if (houseNo.isEmpty()) {
                Toast.makeText(baseContext, "House number field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val address = Address(streetName = street, pincode = zipCode, city = city, type = houseType, houseNo = houseNo)
                presenter.addAddress(address)
            }
            clearText()
        }
    }

    private fun clearText() {
        binding.apply {
            edtCity.text = null
            edtStreetName.text = null
            edtZipCode.text = null
            edtType.text = null
            edtHouseNo.text = null
        }
    }

    override fun setResult(message: String) {
        if (message == "success") {
            Snackbar.make(
                binding.addressLayout,
                "Address added successfully",
                Snackbar.LENGTH_SHORT
            )
                .show()
        } else {
            Snackbar.make(binding.addressLayout, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}