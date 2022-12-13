package com.example.mvpfirstproject.model.remote.data

data class RegisterResponse(
    val count: Int,
    val `data`: List<RegisterData>,
    val error: Boolean
)

data class RegisterData(
    val firstName: String,
    val mobile: String,
    val email: String,
    val password: String,

    val __v: String = "",
    val _id: String = "",
    val createdAt: String = "",
    val gender: String = "",
)



