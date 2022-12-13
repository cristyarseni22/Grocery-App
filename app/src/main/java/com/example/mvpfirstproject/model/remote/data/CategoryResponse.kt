package com.example.mvpfirstproject.model.remote.data

data class CategoryResponse(
    val count: Int,
    val `data`: List<CategoryData>,
    val error: Boolean
)

data class CategoryData(
    val __v: Int = -1,
    val _id: String = "",
    val catDescription: String = "",
    val catId: Int = -1,
    val catImage: String,
    val catName: String,
    val position: Int = -1,
    val slug: String = "",
    val status: Boolean = false
)