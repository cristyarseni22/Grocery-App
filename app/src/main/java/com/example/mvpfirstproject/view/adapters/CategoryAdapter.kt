package com.example.mvpfirstproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ItemCategoryViewBinding
import com.example.mvpfirstproject.model.remote.Constants.IMAGE_URL
import com.example.mvpfirstproject.model.remote.data.CategoryData

class CategoryAdapter(
    private val context: Context,
    private val category: List<CategoryData>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var binding: ItemCategoryViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCategoryViewBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            val cat = category[position]

             bind(cat)

        }
    }

    override fun getItemCount() = category.size

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(category: CategoryData) {
            val url = IMAGE_URL + category.catImage
            try {
                Glide.with(context).load(url)
                    .into(binding.ivCategory)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

            binding.tvCategoryName.text = category.catName

            Glide.with(binding.root).load(url)
                .placeholder(R.drawable.ic_round_warning_24).into(binding.ivCategory)
        }
    }
}