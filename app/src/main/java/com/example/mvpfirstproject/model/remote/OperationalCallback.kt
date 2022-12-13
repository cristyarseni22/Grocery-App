package com.example.mvpfirstproject.model.remote

import com.example.mvpfirstproject.model.remote.data.CategoryResponse

interface OperationalCallback {
    fun onSuccess(message: String)
    fun onFailure(message: String)
}

interface CategoryCallback {
    fun onSuccess(response: CategoryResponse)
    fun onFailure(result: CategoryResponse)
}