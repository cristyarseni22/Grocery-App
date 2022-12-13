package com.example.mvpfirstproject.presenter.category

import com.example.mvpfirstproject.model.remote.CategoryCallback
import com.example.mvpfirstproject.model.remote.data.CategoryResponse
import com.example.mvpfirstproject.model.remote.handlers.CategoryVolleyHandler

class CategoryPresenter(
    private val volleyHandler: CategoryVolleyHandler,
    private val categoryView: CategoryMVP.CategoryView
) : CategoryMVP.CategoryPresenter {
    override fun categoryCall(): String {
        categoryView.onLoad(true)
        val message = volleyHandler.categoryCall(object : CategoryCallback {
            override fun onSuccess(response: CategoryResponse) {
                categoryView.onLoad(false)
                categoryView.setResult(response)
            }

            override fun onFailure(result: CategoryResponse) {
                categoryView.onLoad(false)
                categoryView.setResult(result)
            }
        })
        return message
    }
}