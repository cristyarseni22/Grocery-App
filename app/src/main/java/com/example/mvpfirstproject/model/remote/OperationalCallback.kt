package com.example.mvpfirstproject.model.remote

import com.example.mvpfirstproject.model.remote.data.CategoryResponse
import com.example.mvpfirstproject.model.remote.data.SearchResponse
import com.example.mvpfirstproject.model.remote.data.SubcategoryResponse

interface OperationalCallback {
    fun onSuccess(message: String)
    fun onFailure(message: String)
}

interface CategoryOperationalCallback {
    fun onSuccess(response: CategoryResponse)
    fun onFailure(result: CategoryResponse)
}

interface SearchOperationalCallback {
    fun onSuccess(searchResponse: SearchResponse)
    fun onFailure(message: String)
}

interface SubcategoryOperationalCallback {
    fun onSuccess(subcategoryResponse: SubcategoryResponse)
    fun onFailure(message: String)
}