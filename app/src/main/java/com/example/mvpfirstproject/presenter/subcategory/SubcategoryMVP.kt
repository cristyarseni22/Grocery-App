package com.example.mvpfirstproject.presenter.subcategory

import com.example.mvpfirstproject.model.remote.data.SubcategoryResponse

interface SubcategoryMVP {

    interface SubCategoryPresenter {
        fun getSubcategories(): String
    }

    interface SubCategoryView {
        fun setResult(subcategory: SubcategoryResponse)
        fun onLoad(isLoading: Boolean)
    }
}