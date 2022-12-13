package com.example.mvpfirstproject.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ActivityDashboardBinding
import com.example.mvpfirstproject.model.remote.Constants.FIRST_NAME
import com.example.mvpfirstproject.model.remote.data.CategoryData
import com.example.mvpfirstproject.model.remote.data.CategoryResponse
import com.example.mvpfirstproject.model.remote.handlers.CategoryVolleyHandler
import com.example.mvpfirstproject.presenter.category.CategoryMVP
import com.example.mvpfirstproject.presenter.category.CategoryPresenter
import com.example.mvpfirstproject.view.adapters.CategoryAdapter
import com.google.android.material.snackbar.Snackbar


class DashboardActivity : AppCompatActivity(), CategoryMVP.CategoryView {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var presenter: CategoryMVP.CategoryPresenter
    private lateinit var categoryAdapter: CategoryAdapter
    lateinit var headerView: View
    lateinit var username: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CategoryPresenter(CategoryVolleyHandler(this), this)
        presenter.categoryCall()

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        headerView = binding.navView.getHeaderView(0)

        username = headerView.findViewById(R.id.nav_header_name)

        val pref = getSharedPreferences("login_details", MODE_PRIVATE)
        username.text = pref.getString("name", FIRST_NAME)

        binding.navView.setNavigationItemSelectedListener {
            handleNavigationOperation(it)
            true
        }

    }

    private fun handleNavigationOperation(menuItem: MenuItem) {


        when (menuItem.itemId) {
            R.id.nav_logout -> {
                val logoutDialog = logoutAlertDialog()
                logoutDialog.show()
            }
//            R.id.nav_my_cart ->{
//                startActivity(Intent(baseContext, CartActivity::class.java))
//            }
//            R.id.nav_profile ->{
//                startActivity(Intent(baseContext,ProfileActivity::class.java))
//            }
//            R.id.nav_my_order ->{
//                startActivity(Intent(this, OrderActivity::class.java))
//            }
//            R.id.nav_home ->{
//                startActivity(Intent(this, DashboardActivity::class.java))
//            }

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun logoutAlertDialog(): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Leaving Already?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val pref = getSharedPreferences("login_details", MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
        alertDialog.setNegativeButton("No") { _, _ ->
        }
        return alertDialog
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setResult(result: CategoryResponse) {
        categoryAdapter = CategoryAdapter(this, result.data)

        val categoryList = ArrayList<CategoryData>()
        for (i in result.data.indices) {
            categoryList.add(result.data[i])
            binding.rvCategory.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCategory.adapter = categoryAdapter
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