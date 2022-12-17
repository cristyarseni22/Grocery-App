package com.example.mvpfirstproject.model.remote.data

import java.io.Serializable

data class ProductDetailsResponse(
    val `data`: ProductDetails,
    val error: Boolean
)

data class ProductDetails(
    val __v: Int,
    val _id: String,
    val catId: Int,
    val created: String,
    val description: String,
    val image: String,
    val mrp: Int,
    val position: Int,
    val price: Int,
    val productName: String,
    val quantity: Int,
    val status: Boolean,
    val subId: Int,
    val unit: String
) : Serializable {
    companion object {
        const val KEY_PRODUCT_DETAILS = "productDetails"
    }
}