package com.example.mvpfirstproject.model.remote.handlers

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvpfirstproject.model.remote.Constants.BASE_URL
import com.example.mvpfirstproject.model.remote.Constants.EMAIL
import com.example.mvpfirstproject.model.remote.Constants.FIRST_NAME
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_END_POINT
import com.example.mvpfirstproject.model.remote.Constants.PASSWORD
import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.LoginData
import org.json.JSONObject

class LoginVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun loginUser(user: LoginData, callback: OperationalCallback): String {

        val url = BASE_URL + LOGIN_END_POINT
        val data = JSONObject()

        var message: String? = null

        data.put(EMAIL, user.email)
        data.put(PASSWORD, user.password)
        data.put(FIRST_NAME, user.firstName)


        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            { _ ->
                message = "success"
                callback.onSuccess(message.toString())
            },
            { error ->
                if (error.networkResponse.statusCode != 200) {
                    message = "Wrong Credentials"
                } else if (error.networkResponse.statusCode == 500) {
                    message = "Illegal arguments: undefined, string"
                }
                callback.onFailure(message.toString())
            }
        )
        requestQueue.add(request)
        return message.toString()
    }
}