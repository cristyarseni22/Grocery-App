package com.example.mvpfirstproject.model.remote.data

import java.io.Serializable

data class AddressResponse(
    val addresses: List<AddressData>,
    val message: String,
    val status: Int
)

data class AddressData(
    val address: String,
    val address_id: String,
    val title: String

) : Serializable {
    companion object {
        const val KEY_ADDRESS = "address"
    }
}