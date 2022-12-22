package com.example.mvpfirstproject.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvpfirstproject.databinding.ActivityPaymentBinding
import com.example.mvpfirstproject.model.remote.data.AddressData

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    lateinit var addressData: AddressData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }

        init()

        val intent = intent
        addressData = intent.getSerializableExtra(AddressData.KEY_ADDRESS) as AddressData

        binding.buttonPayment.setOnClickListener {
            val intent = Intent(this, OrderConfirmationActivity::class.java)
            intent.putExtra(AddressData.KEY_ADDRESS, addressData)
            startActivity(intent)
        }
    }

    private fun init() {

        binding.checkboxCash.setOnClickListener {
            if (binding.checkboxCash.isChecked) {
                binding.checkboxInternalBanking.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
                binding.checkboxPaypal.isChecked = false
            }
        }

        binding.checkboxInternalBanking.setOnClickListener {
            if (binding.checkboxInternalBanking.isChecked) {
                binding.checkboxCash.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
                binding.checkboxPaypal.isChecked = false
            }
        }

        binding.checkboxDebitCredit.setOnClickListener {
            if (binding.checkboxDebitCredit.isChecked) {
                binding.checkboxCash.isChecked = false
                binding.checkboxPaypal.isChecked = false
                binding.checkboxInternalBanking.isChecked = false
            }
        }

        binding.checkboxPaypal.setOnClickListener {
            if (binding.checkboxPaypal.isChecked) {
                binding.checkboxCash.isChecked = false
                binding.checkboxInternalBanking.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
            }
        }
    }
}