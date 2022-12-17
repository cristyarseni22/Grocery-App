package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvpfirstproject.databinding.ActivityRegisterBinding
import com.example.mvpfirstproject.model.remote.data.RegisterData
import com.example.mvpfirstproject.model.remote.handlers.RegisterVolleyHandler
import com.example.mvpfirstproject.presenter.register.RegistrationMVP
import com.example.mvpfirstproject.presenter.register.RegistrationPresenter

class RegisterActivity : AppCompatActivity(), RegistrationMVP.RegistrationView {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegistrationMVP.RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpEvents()

        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setUpEvents() {
        presenter = RegistrationPresenter(RegisterVolleyHandler(this), this)
        binding.apply {
            btnRegister.setOnClickListener {
                val name = edtName.text.toString()
                val mobile = edtMobile.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (name.isEmpty()) {
                    Toast.makeText(baseContext, "Cannot have an empty Name", Toast.LENGTH_SHORT)
                        .show()
                } else if (mobile.isEmpty()) {
                    Toast.makeText(baseContext, "Cannot have empty mobile", Toast.LENGTH_SHORT)
                        .show()
                } else if (email.isEmpty()) {
                    Toast.makeText(baseContext, "Cannot have empty email", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.isEmpty()) {
                    Toast.makeText(baseContext, "Cannot have empty password", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val user = RegisterData(name, mobile, email, password)
                    presenter.registerUser(user)
                }
            }
        }
    }

    private fun clearText() {
        binding.apply {
            edtName.text = null
            edtMobile.text = null
            edtEmail.text = null
            edtPassword.text = null
        }
    }

    override fun setResult(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}