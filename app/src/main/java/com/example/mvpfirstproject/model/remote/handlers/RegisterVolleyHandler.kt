package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import android.provider.SimPhonebookContract.SimRecords.PHONE_NUMBER
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.EMAIL
import com.example.mvpfirstproject.model.remote.Constants.FIRST_NAME
import com.example.mvpfirstproject.model.remote.Constants.MOBILE
import com.example.mvpfirstproject.model.remote.Constants.PASSWORD
import com.example.mvpfirstproject.model.remote.Constants.REGISTRATION_END_POINT
import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.RegisterData
import org.json.JSONObject

class RegisterVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun registerUser(user: RegisterData, callback: OperationalCallback): String {

        val url = BASE_URL + REGISTRATION_END_POINT
        val data = JSONObject()

        var message: String? = null

        data.put(FIRST_NAME, user.firstName)
        data.put(MOBILE, user.mobile)
        data.put(EMAIL, user.email)
        data.put(PASSWORD, user.password)


        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            { response ->
                message = response.getString("message")
                callback.onSuccess(message.toString())
            },
            { error ->
                error.printStackTrace()
                callback.onFailure(message.toString())
            }
        )
        requestQueue.add(request)
        return message.toString()
    }

}