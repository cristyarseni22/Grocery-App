package com.example.mvpfirstproject.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.databinding.ItemSubcategoryProductsViewBinding
import com.example.mvpfirstproject.model.remote.data.SubcategoryData
import com.example.mvpfirstproject.view.adapters.viewHolder.SubcategoryProductsViewHolder

class ProductViewPagerAdapter(
    private val subcategory: List<SubcategoryData>
) : RecyclerView.Adapter<SubcategoryProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubcategoryProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSubcategoryProductsViewBinding.inflate(layoutInflater, parent, false)
        return SubcategoryProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubcategoryProductsViewHolder, position: Int) {
        holder.bind(subcategory[position])
    }

    override fun getItemCount(): Int {
        return subcategory.size
    }
}
