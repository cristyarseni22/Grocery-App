package com.example.mvpfirstproject.model.remote.data

data class LoginResponse(
    val token: String,
    val user: LoginData
)

data class LoginData(
    val __v: Int = -1,
    val _id: String = "",
    val createdAt: String = "",
    val email: String,
    val firstName: String = "",
    val mobile: String = "",
    val password: String
)