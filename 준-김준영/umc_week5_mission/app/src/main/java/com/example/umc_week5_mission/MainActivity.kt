package com.example.umc_week5_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week5_mission.databinding.ActivityMainBinding
import com.example.umc_week5_mission.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchList = arrayListOf(
            Switch(R.drawable.turn, switch = "1"),
            Switch(R.drawable.turn, switch = "2"),
            Switch(R.drawable.turn, switch = "3"),
            Switch(R.drawable.turn, switch = "4"),
            Switch(R.drawable.turn, switch = "5"),
            Switch(R.drawable.turn, switch = "6"),
            Switch(R.drawable.turn, switch = "7"),
            Switch(R.drawable.turn, switch = "8"),
            Switch(R.drawable.turn, switch = "9"),
            Switch(R.drawable.turn, switch = "10")
        )
        binding.rvSwitch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSwitch.setHasFixedSize(true)

        binding.rvSwitch.adapter = SwitchAdapter(switchList)
    }
}