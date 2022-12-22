package com.example.mvpfirstproject.model.remote.data

data class OrderRequest(
    val count: Int,
    val `data`: List<Order>,
    val error: Boolean
)

data class Order(
    val __v: Int,
    val _id: String,
    val date: String,
    val orderStatus: String,
    val orderSummary: OrderSummary,
    val products: List<Product>,
    val shippingAddress: ShippingAddress,
    val user: User,
    val userId: String
)

data class OrderSummary(
    val _id: String,
    val deliveryCharges: Int,
    val discount: Int,
    val ourPrice: Int,
    val totalAmount: Int
)

data class Product(
    val _id: String,
    val price: Int,
    val productName: String,
    val quantity: Int
)

data class ShippingAddress(
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String
)

data class User(
    val _id: String,
    val email: String
)