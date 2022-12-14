package com.example.mvpfirstproject.presenter.category

import com.example.mvpfirstproject.model.remote.data.CategoryResponse

interface CategoryMVP {
    interface CategoryPresenter {
        fun categoryCall(): String
    }

    interface CategoryView {
        fun setCategoryResult(result: CategoryResponse)
        fun onLoad(isLoading: Boolean)
    }
}