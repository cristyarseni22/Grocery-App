package com.example.mvpfirstproject.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpfirstproject.databinding.ViewHolderAddressBinding
import com.example.mvpfirstproject.model.remote.data.AddressData
import com.example.mvpfirstproject.view.activities.PaymentActivity

class AddressAdapter(var addressList: List<AddressData>) :
    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderAddressBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val address: AddressData = addressList[position]
        holder.bind(address, position)
    }

    inner class MyViewHolder(val binding: ViewHolderAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(address: AddressData, position: Int) {
            binding.tvTitle.text = address.title
            binding.tvAddress.text = address.address

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PaymentActivity::class.java)
                intent.putExtra(AddressData.KEY_ADDRESS, address)
                itemView.context.startActivity(intent)
            }
        }
    }
}