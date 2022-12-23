package com.example.mvpfirstproject.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ActivityDashboardBinding
import com.example.mvpfirstproject.model.remote.Constants.LOGIN_DETAILS
import com.example.mvpfirstproject.model.remote.data.CategoryData
import com.example.mvpfirstproject.model.remote.data.CategoryResponse
import com.example.mvpfirstproject.model.remote.data.SearchData
import com.example.mvpfirstproject.model.remote.data.SearchResponse
import com.example.mvpfirstproject.model.remote.handlers.CategoryVolleyHandler
import com.example.mvpfirstproject.model.remote.handlers.SearchVolleyHandler
import com.example.mvpfirstproject.presenter.category.CategoryMVP
import com.example.mvpfirstproject.presenter.category.CategoryPresenter
import com.example.mvpfirstproject.presenter.search.SearchMVP
import com.example.mvpfirstproject.presenter.search.SearchPresenter
import com.example.mvpfirstproject.view.adapters.CategoryAdapter
import com.example.mvpfirstproject.view.adapters.SearchAdapter


class DashboardActivity : AppCompatActivity(), CategoryMVP.CategoryView, SearchMVP.SearchView {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var presenter: CategoryMVP.CategoryPresenter
    private lateinit var categoryAdapter: CategoryAdapter
    lateinit var headerView: View
    lateinit var username: TextView
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var searchAdapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CategoryPresenter(CategoryVolleyHandler(this), this)
        presenter.categoryCall()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        binding.navView.setNavigationItemSelectedListener {
            handleNavigationOperation(it)
            true
        }

        setupSearchEvents()
    }

    override fun setCategoryResult(result: CategoryResponse) {
        categoryAdapter = CategoryAdapter(this, result.data)
        categoryAdapter.setOnOptionSelectListener { category, _ ->
            val intent = Intent(baseContext, SubcategoryActivity::class.java)
            intent.putExtra(CategoryData.KEY_CATEGORY, category.catId)
            startActivity(intent)
            finish()
        }

        val categoryList = ArrayList<CategoryData>()
        for (i in result.data.indices) {
            categoryList.add(result.data[i])
            binding.rvCategory.layoutManager =
                GridLayoutManager(this, 2)
            binding.rvCategory.adapter = categoryAdapter
        }

        binding.tvCategory.setOnClickListener {
            binding.rvCategory.adapter = categoryAdapter
        }
    }

    private fun handleNavigationOperation(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_logout -> {
                val logoutDialog = logoutAlertDialog()
                logoutDialog.show()
            }
            R.id.nav_home -> {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            R.id.nav_my_cart -> {
                startActivity(Intent(baseContext, CartActivity::class.java))
            }
            R.id.nav_profile ->{
                startActivity(Intent(baseContext,ProfileActivity::class.java))
            }
            R.id.nav_my_order ->{
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
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

    private fun setupSearchEvents() {
        searchPresenter = SearchPresenter(SearchVolleyHandler(this), this)
        binding.apply {

            btnShowSearch.setOnClickListener {
                if (hiddenSearch.isVisible) {
                    hiddenSearch.visibility = View.GONE
                } else hiddenSearch.visibility = View.VISIBLE
            }
            binding.btnSearch.setOnClickListener {
                if (!edtSearch.text.isNullOrEmpty()) {
                    searchPresenter.searchProduct(edtSearch.text.toString().trim())
                    hiddenSearch.visibility = View.GONE
                } else {
                    Toast.makeText(baseContext, "Enter a product to search for", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun searchResult(searchResponse: SearchResponse) {
        val searchList = mutableListOf<SearchData>()
        for (i in searchResponse.data.indices) {
            searchList.add(searchResponse.data[i])
        }
        searchAdapter = SearchAdapter(this, searchList)
        binding.apply {
            rvCategory.layoutManager = GridLayoutManager(baseContext, 2)
            rvCategory.adapter = searchAdapter
        }
    }

    private fun logoutAlertDialog(): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Leaving Already?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val pref = getSharedPreferences(LOGIN_DETAILS, MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
        alertDialog.setNegativeButton("No") { _, _ ->
        }
        return alertDialog
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}