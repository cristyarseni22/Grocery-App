package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mvpfirstproject.databinding.ActivityProfileBinding
import com.example.mvpfirstproject.model.remote.Constants

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.buttonGoShopping.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.buttonLogoutProfile.setOnClickListener {
            val logoutDialog = logoutAlertDialog()
            logoutDialog.show()
        }
    }

    private fun logoutAlertDialog(): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Leaving Already?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val pref = getSharedPreferences(Constants.LOGIN_DETAILS, MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
        alertDialog.setNegativeButton("No") { _, _ ->
        }
        return alertDialog
    }
}