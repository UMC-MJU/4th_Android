package com.example.umc_week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week5.databinding.ActivityMainBinding
import com.example.umc_week5.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileList = arrayListOf(
            Profiles(R.drawable.man, name = "김준영",24, "안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, name = "김이슬",22, "리액트 앱 개발자"),
            Profiles(R.drawable.man, name = "송범준",31, "서버 개발자"),
            Profiles(R.drawable.man, name = "이승한",42, "스위프트 개발자"),
            Profiles(R.drawable.man, name = "고승준",20, "웹 앱 개발자"),
            Profiles(R.drawable.woman, name = "문하늘",28, "하이브리드 개발자"),
            Profiles(R.drawable.man, name = "김승겸",21, "풀스택 개발자"),
            Profiles(R.drawable.man, name = "유강민",43, "ios 개발자"),
            Profiles(R.drawable.woman, name = "이한나",22, "프론트 개발자"),
            Profiles(R.drawable.woman, name = "유인나",21, "백엔드 개발자"),
            Profiles(R.drawable.man, name = "고하림",28, "자바 개발자"),
            Profiles(R.drawable.man, name = "임성혁",22, "게임 개발자")
        )
        binding.rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvProfile.setHasFixedSize(true)

        binding.rvProfile.adapter = ProfileAdapter(profileList)




    }
}