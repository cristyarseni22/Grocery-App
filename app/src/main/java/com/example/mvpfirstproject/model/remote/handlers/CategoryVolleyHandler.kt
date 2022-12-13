package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.CategoryCallback
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.Constants.CATEGORY_END_POINT
import com.example.mvpfirstproject.model.remote.data.CategoryResponse
import com.google.gson.Gson

class CategoryVolleyHandler(private val context: Context) {

    private lateinit var requestQueue: RequestQueue

    fun categoryCall(callback: CategoryCallback): String {

        requestQueue = Volley.newRequestQueue(context)
        val url = Constants.BASE_URL + CATEGORY_END_POINT
        var message: String? = null

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val categoryResponse: CategoryResponse =
                    gson.fromJson(it, CategoryResponse::class.java)
                callback.onSuccess(categoryResponse)
                message = "success"
            },
            {
            }
        )
        requestQueue.add(request)
        return message.toString()
    }

//        val request = object : StringRequest(
//            Method.GET,
//            url,
//            { apiResponse: String ->
//                val tokenType = object : TypeToken<CategoryData>() {}
//                val gson = Gson()
//                try {
//                    val categoryResponse: CategoryResponse =
//                        gson.fromJson(apiResponse, tokenType.type)
//                    callback.onSuccess(categoryResponse)
//                } catch (error: java.lang.Exception) {
//                    error.printStackTrace()
//                }
//
//
////                if (!categoryResponse.error) {
////                callback.onSuccess(categoryResponse)
////                message = "success"
////                } else {
////                callback.onFailure("Something went wrong")
////                }
//            },
//            {
//                callback.onFailure(it.toString())
//            }
//        ) {}
//        requestQueue.add(request)
////        return message.toString()
//    }
}