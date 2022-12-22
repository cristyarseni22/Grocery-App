package com.example.mvpfirstproject.view.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvpfirstproject.databinding.ActivityOrderCheckoutBinding
import com.example.mvpfirstproject.databinding.ActivityOrderConfirmationBinding

class OrderConfirmationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderConfirmationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }


}