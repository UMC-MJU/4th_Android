package com.example.umc_week9_mission1_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week9_mission1_.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.fgMain.id, mainFragment())
            .commitAllowingStateLoss()

        binding.btnWeather.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fgMain.id, weatherFragment())
                .commitAllowingStateLoss()
        }

        binding.btnDust.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fgMain.id, dustFragment())
                .commitAllowingStateLoss()
        }

        binding.btnCovid.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fgMain.id, covidFragment())
                .commitAllowingStateLoss()
        }
    }

}