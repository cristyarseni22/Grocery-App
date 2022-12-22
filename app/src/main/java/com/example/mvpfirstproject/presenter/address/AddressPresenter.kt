package com.example.mvpfirstproject.presenter.address

import com.example.mvpfirstproject.model.remote.OperationalCallback
import com.example.mvpfirstproject.model.remote.data.Address
import com.example.mvpfirstproject.model.remote.handlers.AddressVolleyHandler

class AddressPresenter(
    private val addressVolleyHandler: AddressVolleyHandler,
    private val addressView: AddressMVP.AddressView
) : AddressMVP.AddressPresenter {

    override fun addAddress(address: Address): String {
        addressView.onLoad(true)
        val message = addressVolleyHandler.addAddress(address,
            object : OperationalCallback {
                override fun onSuccess(message: String) {
                    addressView.onLoad(false)
                    addressView.setResult(message)
                }

                override fun onFailure(message: String) {
                    addressView.onLoad(false)
                    addressView.setResult(message)
                }
            }
        )
        return message
    }
}
