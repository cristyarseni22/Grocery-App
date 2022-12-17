package com.example.mvpfirstproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpfirstproject.databinding.ItemSearchViewBinding
import com.example.mvpfirstproject.model.remote.Constants
import com.example.mvpfirstproject.model.remote.data.SearchData

class SearchAdapter(val context: Context, private val searchList: List<SearchData>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding: ItemSearchViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = ItemSearchViewBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            val searchItem = searchList[position]
            bind(searchItem)
        }
    }

    override fun getItemCount() = searchList.size

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(searchItem: SearchData) {
            binding.apply {
                txtName.text = searchItem.productName
//                txtDescription.text = searchItem.description
                txtPrice.text = "\$${searchItem.price}"
                val url = Constants.IMAGE_URL + searchItem.image
                try {
                    Glide.with(context).load(url)
                        .into(binding.ivItem)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}