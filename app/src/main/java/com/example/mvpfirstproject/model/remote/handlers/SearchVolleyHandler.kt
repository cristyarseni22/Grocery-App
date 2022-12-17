package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.SearchOperationalCallback
import com.example.mvpfirstproject.model.remote.data.SearchData
import com.example.mvpfirstproject.model.remote.data.SearchResponse
import com.google.gson.Gson

class SearchVolleyHandler(val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private lateinit var searchList: MutableList<SearchData>

    fun searchProduct(search: String, callback: SearchOperationalCallback) {
        val url = Constants.BASE_URL + Constants.SEARCH_END_POINT + search
        val message: String? = null

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                searchList = mutableListOf()
                val gson = Gson()
                val searchResponse: SearchResponse =
                    gson.fromJson(it, SearchResponse::class.java)
                if (!searchResponse.error) {
                    callback.onSuccess(searchResponse)
                } else
                    callback.onFailure("Nothing was found")
            },
            { error: VolleyError ->
                error.printStackTrace()
                callback.onFailure(message.toString())
            }
        )
        requestQueue.add(request)
    }
}