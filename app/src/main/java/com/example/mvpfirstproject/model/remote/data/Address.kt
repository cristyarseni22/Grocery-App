package com.example.mvpfirstproject.model.remote.data

import java.io.Serializable

data class Address(
    val error: Boolean = false,
    val message: String = "",

    val pincode: String = "",
    val city: String = "",
    val streetName: String = "",
    val houseNo: String = "",
    val type: String = "",
    val userId: String = ""
) : Serializable {
    companion object {
        const val KEY_ADDRESS = "address"
    }
}