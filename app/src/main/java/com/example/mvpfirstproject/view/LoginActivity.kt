package com.example.mvpfirstproject.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvpfirstproject.databinding.ActivityLoginBinding
import com.example.mvpfirstproject.model.remote.Constants.EMAIL
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.Constants.PASSWORD
import com.example.mvpfirstproject.model.remote.data.LoginData
import com.example.mvpfirstproject.model.remote.handlers.LoginVolleyHandler
import com.example.mvpfirstproject.presenter.login.LoginMVP
import com.example.mvpfirstproject.presenter.login.LoginPresenter
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity(), LoginMVP.LoginView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginMVP.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpEvents()

        binding.tvNewUser.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
        }
    }

    private fun setUpEvents() {
        val loginHandler = LoginVolleyHandler(this)
        presenter = LoginPresenter(loginHandler, this)
        binding.apply {
            btnLogin.setOnClickListener {

                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(baseContext, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(baseContext, "Password cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val user = LoginData(email = email, password = password)
                    presenter.loginUser(user)
                }
            }
        }
    }


    override fun setResult(message: String) {
        if (message == "success") {
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()

            val loginSharedPref =
                this.getSharedPreferences(LOGIN_DETAILS, Context.MODE_PRIVATE)

            val editor = loginSharedPref.edit()
            editor.putString(EMAIL, binding.edtEmail.text.toString())
            editor.putString(PASSWORD, binding.edtPassword.text.toString())
            editor.apply()
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}