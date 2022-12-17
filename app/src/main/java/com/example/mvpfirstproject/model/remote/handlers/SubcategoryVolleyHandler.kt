package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.SUBCATEGORY_END_POINT
import com.example.mvpfirstproject.model.remote.SubcategoryOperationalCallback
import com.example.mvpfirstproject.model.remote.data.SubcategoryResponse
import com.google.gson.Gson

class SubcategoryVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun getSubCategories(categoryId: String, callback: SubcategoryOperationalCallback) {
        val url = BASE_URL + SUBCATEGORY_END_POINT + categoryId
        val message: String? = null

        val strRequest = object : StringRequest(
            Method.GET,
            url,
            {
                val gson = Gson()
                val subcategoryResponse: SubcategoryResponse =
                    gson.fromJson(it, SubcategoryResponse::class.java)
                callback.onSuccess(subcategoryResponse)

                if (!subcategoryResponse.error) {
                    callback.onSuccess(subcategoryResponse)
                } else
                    callback.onFailure("Nothing was found")
            },
            { error: VolleyError ->
                error.printStackTrace()
                callback.onFailure(message.toString())
            }
        ) {}
        requestQueue.add(strRequest)
    }
}