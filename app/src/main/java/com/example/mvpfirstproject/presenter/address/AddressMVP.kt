package com.example.mvpfirstproject.presenter.address

import com.example.mvpfirstproject.model.remote.data.Address
import com.example.mvpfirstproject.model.remote.data.LoginData


interface AddressMVP {

    interface AddressPresenter {
        fun addAddress(address: Address): String
    }

    interface AddressView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}