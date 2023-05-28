package com.example.umc_week9_mission2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week9_mission2.R
import com.example.umc_week9_mission2.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("TAG", "사용자 정보 요청 실패 $error")
            } else if (user != null) {
                Log.d("TAG", "사용자 정보 요청 성공 : $user")
                binding.tvName.text = user.kakaoAccount?.profile?.nickname
                binding.tvAge.text = user.kakaoAccount?.ageRange.toString()
                binding.tvEmail.text = user.kakaoAccount?.email
            }
        }
        binding.btnLogout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e("TAG", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                } else {
                    Log.i("TAG", "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }
        }

    }
}