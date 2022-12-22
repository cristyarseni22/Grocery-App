package com.example.mvpfirstproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.R
import com.example.mvpfirstproject.databinding.ViewHolderCartBinding
import com.example.mvpfirstproject.model.local.sql.ProductDao
import com.example.mvpfirstproject.model.remote.Constants.IMAGE_URL
import com.example.mvpfirstproject.model.remote.data.ProductsData
import com.example.mvpfirstproject.view.adapters.viewHolder.SubcategoryProductsViewHolder
import com.squareup.picasso.Picasso

class CartAdapter(var mContext: Context, var productList: MutableList<ProductsData>) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var listener: OnAdapterInteraction? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = ViewHolderCartBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product: ProductsData = productList[position]
        holder.bind(product, position)
    }

    inner class MyViewHolder(val binding: ViewHolderCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductsData, position: Int) {
            binding.textViewCartName.text = product.productName
            binding.textViewCartPrice.text = "$" + product.price.toString()

            Picasso.get()
                .load(IMAGE_URL + product.image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimage)
                .into(binding.imageProductCart)

            binding.tvQtyu.text = product.quantity.toString()

            binding.buttonDelete.setOnClickListener {
                listener?.onClickedItemListener(it, position, product._id)
            }
        }
    }

    interface OnAdapterInteraction {
        fun onClickedItemListener(view: View, position: Int, id: String)
    }

    fun setAdapterListener(onAdapterInteraction: OnAdapterInteraction) {
        listener = onAdapterInteraction
    }

    fun setData(list: MutableList<ProductsData>) {
        productList = list
        notifyDataSetChanged()
    }
}
