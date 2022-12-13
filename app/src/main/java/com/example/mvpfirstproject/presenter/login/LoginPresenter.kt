package com.example.mvpfirstproject.presenter.login

import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.*
import com.example.mvpfirstproject.model.remote.handlers.LoginVolleyHandler

class LoginPresenter(
    private val loginVolleyHandler: LoginVolleyHandler,
    private val loginView: LoginMVP.LoginView
) : LoginMVP.LoginPresenter {

    override fun loginUser(user: LoginData): String {
        loginView.onLoad(true)
        val message = loginVolleyHandler.loginUser(user,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message)
                }

                override fun onFailure(message: String) {
                    loginView.onLoad(false)
                    loginView.setResult(message)
                }

            }
        )
        return message
    }
}