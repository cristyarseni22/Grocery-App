package com.example.mvpfirstproject.model.remote.data

import java.io.Serializable

class ProductsResponse(
    val count: Int,
    val data: List<ProductsData>,
    val error: Boolean
)

data class ProductsData(
    val __v: Int = -1,
    val _id: String = "",
    val catId: Int = -1,
    val created: String = "",
    val description: String = "",
    val image: String = "",
    val mrp: Double = -1.0,
    val position: Int = -1,
    val price: Double = -1.0,
    val productName: String = "",
    var quantity: Int = -1,
    val status: Boolean = false,
    val subId: Int = -1,
    val unit: String = ""
) : Serializable {
    companion object {
        const val KEY_PRODUCT = "product"
    }
}