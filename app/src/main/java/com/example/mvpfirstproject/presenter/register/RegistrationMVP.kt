package com.example.mvpfirstproject.presenter.register

import com.example.mvpfirstproject.model.remote.data.RegisterData

interface RegistrationMVP {

    interface RegistrationPresenter {
        fun registerUser(user: RegisterData): String
    }

    interface RegistrationView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}