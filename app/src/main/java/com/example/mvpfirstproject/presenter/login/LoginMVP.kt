package com.example.mvpfirstproject.presenter.login

import com.example.mvpfirstproject.model.remote.data.LoginData

interface LoginMVP {

    interface LoginPresenter {
        fun loginUser(user: LoginData): String
    }

    interface LoginView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}