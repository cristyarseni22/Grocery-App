package com.example.mvpfirstproject.model.remote.data

data class AddressResponse(
    val count: Int,
    val `data`: List<AddressData>,
    val error: Boolean
)

data class AddressData(
    val __v: Int,
    val _id: String,
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String,
    val userId: String
) : java.io.Serializable {
    companion object {
        const val KEY_ADDRESS = "KEY ADDRESS"
    }
}