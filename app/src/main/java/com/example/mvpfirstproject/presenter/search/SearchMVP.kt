package com.example.mvpfirstproject.presenter.search

import com.example.mvpfirstproject.model.remote.data.SearchResponse

interface SearchMVP {
    interface SearchPresenter {
        fun searchProduct(search: String): String
    }

    interface SearchView {
        fun searchResult(searchResponse: SearchResponse)
        fun onLoad(isLoading: Boolean)
    }
}