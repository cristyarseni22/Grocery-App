package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.mvpfirstproject.databinding.ActivitySpashScreenBinding
import com.example.mvpfirstproject.model.remote.Constants.EMAIL
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.Constants.PASSWORD

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time: Long = 2000
        Handler().postDelayed(Runnable {
            verifyLogin()
        }, time)
    }

    private fun verifyLogin() {
        val pref = getSharedPreferences(LOGIN_DETAILS, MODE_PRIVATE)
        if (pref.contains(EMAIL) && pref.contains(PASSWORD)) {
            binding.imageView.setOnClickListener {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            startActivity(Intent(baseContext, DashboardActivity::class.java))
        } else {
            binding.imageView.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
    }
}