package com.example.umc_week6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_week6_2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val mainVPAdapter = MainVPAdapter(this)
        viewBinding.vpMain.adapter = mainVPAdapter

        val tabTitleArray = arrayOf(
            "One",
            "Two"
        )

        TabLayoutMediator(viewBinding.tabMain, viewBinding.vpMain){ tab, position -> // tab: 상단의 탭 / position: 탭의 인덱스
            tab.text = tabTitleArray[position]
        }.attach()
    }
}