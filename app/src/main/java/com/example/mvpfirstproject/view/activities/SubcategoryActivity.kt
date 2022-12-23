package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvpfirstproject.databinding.ActivitySubcategoryBinding
import com.example.mvpfirstproject.model.remote.data.*
import com.example.mvpfirstproject.model.remote.handlers.SubcategoryVolleyHandler
import com.example.mvpfirstproject.presenter.subcategory.SubcategoryMVP
import com.example.mvpfirstproject.presenter.subcategory.SubcategoryPresenter
import com.example.mvpfirstproject.view.adapters.ProductViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubcategoryActivity : AppCompatActivity(), SubcategoryMVP.SubCategoryView {
    private lateinit var binding: ActivitySubcategoryBinding
    private lateinit var productAdapter: ProductViewPagerAdapter
    private lateinit var subcategoryPresenter: SubcategoryPresenter
    private lateinit var subcategories: List<SubcategoryData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subcategoryPresenter = SubcategoryPresenter(SubcategoryVolleyHandler(this), this)
        subcategoryPresenter.getSubcategories()

        binding.apply {
            ivBack.setOnClickListener {
                startActivity(Intent(baseContext, DashboardActivity::class.java))
            }
            ivGoToCart.setOnClickListener {
                startActivity(Intent(baseContext, CartActivity::class.java))
            }
        }
    }

    override fun setResult(result: SubcategoryResponse) {
        subcategories = result.data
        productAdapter = ProductViewPagerAdapter(result.data)
        binding.viewPager.adapter = productAdapter

        val mediator = TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = subcategories[position].subName
        }
        mediator.attach()
    }

    override fun onLoad(isLoading: Boolean) {
    }
}