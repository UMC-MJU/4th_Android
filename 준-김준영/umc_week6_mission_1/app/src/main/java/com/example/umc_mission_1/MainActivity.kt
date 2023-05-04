package com.example.umc_mission_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_mission_1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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
            "Menu",
            "Home",
            "MyPage"
        )

        TabLayoutMediator(viewBinding.tapMain, viewBinding.vpMain){tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()




        // 네비게이션 메뉴 설정
        viewBinding.navBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_menu -> {
                    viewBinding.vpMain.currentItem = 0
                    true
                }
                R.id.menu_home -> {
                    viewBinding.vpMain.currentItem = 1
                    true
                }
                R.id.menu_mypage -> {
                    viewBinding.vpMain.currentItem = 2
                    true
                }
                else -> false
            }
        }

        // 뷰페이저 슬라이드 이벤트 처리
        viewBinding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // 뷰페이저 페이지 이동시, BottomNavigationView 메뉴를 함께 이동시킴
                when (position) {
                    0 -> viewBinding.navBottom.selectedItemId = R.id.menu_menu
                    1 -> viewBinding.navBottom.selectedItemId = R.id.menu_home
                    2 -> viewBinding.navBottom.selectedItemId = R.id.menu_mypage
                }
            }
        })
    }
}