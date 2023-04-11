package com.example.umc_week4_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_week4_.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val secondIntent = intent.extras
        val data = secondIntent!!["data"] as String

        binding.txtSecond.text = data
    }
}