package com.example.mvpfirstproject.presenter.register

import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.RegisterData
import com.example.mvpfirstproject.model.remote.handlers.RegisterVolleyHandler

class RegistrationPresenter(
    private val registerVolleyHandler: RegisterVolleyHandler,
    private val registrationView: RegistrationMVP.RegistrationView
) : RegistrationMVP.RegistrationPresenter {

    override fun registerUser(user: RegisterData): String {
        registrationView.onLoad(true)
        val message = registerVolleyHandler.registerUser(user,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }

                override fun onFailure(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }
            }
        )
        return message
    }
}