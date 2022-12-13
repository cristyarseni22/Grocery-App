package com.example.mvpfirstproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ActivityMainBinding
import com.example.mvpfirstproject.model.remote.Constants.EMAIL
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.Constants.PASSWORD

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time: Long = 2000
        Handler().postDelayed(Runnable {
            val pref = getSharedPreferences(LOGIN_DETAILS, MODE_PRIVATE)
            if (pref.contains(EMAIL) && pref.contains(PASSWORD)) {

                startActivity(Intent(baseContext, DashboardActivity::class.java))
            } else {
                startActivity(Intent(baseContext, LoginActivity::class.java))
            }
        }, time)

//        startAnimation()
    }

//    private fun startAnimation() {
//        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.combine_three_anim)
//        binding.imageView.startAnimation(rotateAnimation)
//    }
}