package com.example.umc_week10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week10.databinding.ActivityInformBinding
import com.navercorp.nid.NaverIdLoginSDK

class InformAcitivity:AppCompatActivity() {
    private val binding: ActivityInformBinding by lazy {
        ActivityInformBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvName.text = intent.getStringExtra("name")
        binding.tvEmail.text = intent.getStringExtra("email")

        binding.ivNaverLogout.setOnClickListener {
            NaverIdLoginSDK.logout()
            val intent = Intent(this@InformAcitivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}