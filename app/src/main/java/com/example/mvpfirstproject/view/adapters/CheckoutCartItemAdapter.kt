//package com.example.mvpfirstproject.view.adapters
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.mvpfirstproject.R
//import com.example.mvpfirstproject.model.remote.Constants.IMAGE_URL
//
//
//class CheckoutCartItemAdapter(private val context: Context, val infoArrayList: ArrayList<CartItem>) :
//    RecyclerView.Adapter<CheckoutCartItemAdapter.CartItemHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
//        val mView: View =
//            LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart_sample, parent, false)
//        return CartItemHolder(mView)
//    }
//
//    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
//        holder.apply {
//            val info = infoArrayList.get(position)
//            tvCartSampleName.text = info.productName
//            tvCartSampleAmount.text = (info.price * info.count).toString()
//            tvCartSampleQuantity.text = info.count.toString()
//            tvCartSamplePrice.text = "$ ${info.price}"
//
//            Glide.with(context)
//                .load(IMAGE_URL + info.productImageUrl)
//                .into(ivCartSampleIm)
//
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return infoArrayList.size
//    }
//
//
//    inner class CartItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val ivCartSampleIm: ImageView = view.findViewById(R.id.iv_cart_sample_im)
//        val tvCartSampleName: TextView = view.findViewById(R.id.tv_cart_sample_name)
//        val tvCartSamplePrice: TextView = view.findViewById(R.id.tv_cart_sample_price)
//        val tvCartSampleQuantity: TextView = view.findViewById(R.id.tv_cart_sample_quantity)
//        val tvCartSampleAmount: TextView = view.findViewById(R.id.tv_cart_sample_amount)
//    }
//}
