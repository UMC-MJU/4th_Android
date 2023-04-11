package com.example.umc_week4_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week4_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnMain.setOnClickListener{
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "From MainActivity")
            startActivity(intent)
        }

        Log.e("onCreate", "ENTER")
    }
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "ENTER")
    }
    override fun onResume() {
        super.onResume()
        Log.e("onResume", "ENTER")
    }

    override fun onPause() {
        super.onPause()
        Log.e("onPause", "ENTER")
    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop", "ENTER")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "ENTER")
    }
}
