package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.Constants.POST_ADDRESS_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.USER_ID
import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.Address
import org.json.JSONObject

class AddressVolleyHandler(val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun addAddress(address: Address, callback: OperationalCallback): String {
        val url = "$BASE_URL$POST_ADDRESS_END_POINT"
        val userData = JSONObject()
        val message: String? = null
        val sharedPreferences =
            context.getSharedPreferences(LOGIN_DETAILS, Context.MODE_PRIVATE)

        userData.put("pincode", address.pincode)
        userData.put("city", address.city)
        userData.put("streetName", address.streetName)
        userData.put("type", address.type)
        userData.put("houseNo", address.houseNo)
        userData.put("userId", sharedPreferences.getString(USER_ID, ""))

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            userData,
            { response: JSONObject ->
                callback.onSuccess(response.getString("message"))
            },
            { error: VolleyError ->
                error.printStackTrace()
                callback.onFailure(error.toString())
            }
        )
        requestQueue.add(request)
        return message.toString()
    }
}