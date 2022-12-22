package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.ORDER_DETAIL_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.ORDER_LIST_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.ORDER_PLACE_END_POINT
import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.OrderOperationalCallback
import com.example.mvpfirstproject.model.remote.data.Order
import com.example.mvpfirstproject.model.remote.data.OrderRequest
import com.google.gson.Gson
import org.json.JSONObject


class OrderVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun placeOrderToAPI(placeOrderRequest: Order, callback: OperationalCallback): String {
        val url = BASE_URL + ORDER_PLACE_END_POINT
        var message: String? = null
        val gson = Gson()
        val data = JSONObject(gson.toJson(placeOrderRequest))
        val request = JsonObjectRequest(Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val status = response.getInt("status")
                if(status == 0){
                    callback.onSuccess(message.toString())
                } else {
                    callback.onFailure(message.toString())
                }

            }, { error: VolleyError ->
                error.printStackTrace()
                callback.onFailure(message.toString())
            })
        requestQueue.add(request)
        return message.toString()
    }

//    fun getOrdersFromApi(callback: OperationalCallback): String {
//        val sharedPreferences =
//            context.getSharedPreferences(Constants.LOGIN_DETAILS, Context.MODE_PRIVATE)
//        val userId = sharedPreferences.getString(Constants.USER_ID, "")
//
//        val url = BASE_URL + ORDER_DETAIL_END_POINT + userId
//        var message: String? = null
//
//
//        val request = object: StringRequest(Request.Method.GET, url,
//            Response.Listener {
//                val gson = Gson()
//                val orderResponse = gson.fromJson(it.toString(), OrderRequest::class.java)
//                callback.onSuccess(orderResponse)
//                Log.e("tag", it.toString())
//            }, {
//                Log.e("tag", it.toString())
//            }){
//        }
//        requestQueue.add(request)
//        return message.toString()
//    }

//    fun getOrderDetailFromApi(orderId: String, callback: OperationalCallback): String {
//        val url = BASE_URL + GET_ORDER_DETAIL_END_POINT + "order_id=" +orderId
//        Log.e("url", "${url}")
//        var message: String? = null
//
//
//        val request = object: StringRequest(Request.Method.GET, url,
//            Response.Listener {
//                val gson = Gson()
//                val orderDetailResponse = gson.fromJson(it.toString(), OrderDetailResponse::class.java)
//                callback.onSuccess(orderDetailResponse)
//                Log.e("tag", it.toString())
//            }, {
//                Log.e("tag", it.toString())
//            }){
//        }
//        requestQueue.add(request)
//        return message.toString()
//    }
}