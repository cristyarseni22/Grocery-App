package com.example.mvpfirstproject.presenter.subcategory

import com.example.mvpfirstproject.model.remote.SubcategoryOperationalCallback
import com.example.mvpfirstproject.model.remote.data.SubcategoryData
import com.example.mvpfirstproject.model.remote.data.SubcategoryResponse
import com.example.mvpfirstproject.model.remote.handlers.SubcategoryVolleyHandler


class SubcategoryPresenter(
    private val subcategoryVolleyHandler: SubcategoryVolleyHandler,
    private val subCategoryView: SubcategoryMVP.SubCategoryView
) : SubcategoryMVP.SubCategoryPresenter {

    override fun getSubcategories(): String {
        subCategoryView.onLoad(true)
        val message = subcategoryVolleyHandler.getSubCategories(SubcategoryData.KEY_SUB_CATEGORY,object : SubcategoryOperationalCallback {
                override fun onSuccess(subcategoryResponse: SubcategoryResponse) {
                    subCategoryView.onLoad(false)
                    subCategoryView.setResult(subcategoryResponse)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message.toString()
    }
}