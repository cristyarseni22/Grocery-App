package com.example.mvpfirstproject.presenter.search

import com.example.mvpfirstproject.model.remote.SearchOperationalCallback
import com.example.mvpfirstproject.model.remote.data.SearchResponse
import com.example.mvpfirstproject.model.remote.handlers.SearchVolleyHandler

class SearchPresenter(
    private val searchVolleyHandler: SearchVolleyHandler,
    private val searchView: SearchMVP.SearchView
) : SearchMVP.SearchPresenter {
    override fun searchProduct(search: String): String {
        searchView.onLoad(true)
        val message =
            searchVolleyHandler.searchProduct(search, object : SearchOperationalCallback {
                override fun onSuccess(searchResponse: SearchResponse) {
                    searchView.onLoad(false)
                    searchView.searchResult(searchResponse)
                }

                override fun onFailure(message: String) {
                    searchView.onLoad(false)
                }
            })
        return message.toString()
    }

}