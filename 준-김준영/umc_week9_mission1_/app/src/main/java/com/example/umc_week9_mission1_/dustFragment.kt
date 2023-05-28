package com.example.umc_week9_mission1_

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.umc_week9_mission1_.DustApi.DustService
import com.example.umc_week9_mission1_.DustApi.dustRetroApi
import com.example.umc_week9_mission1_.databinding.DustFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class dustFragment : Fragment() {
    private val binding:DustFragmentBinding by lazy {
        DustFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val service = dustRetroApi.dustService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getDust(getString(R.string.dust_api_key),"종로구", "DAILY", "JSON")
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    with(binding){
                        tvDust.text = "미세먼지 농도 : ${response.body()?.response?.body?.items?.get(0)?.pm10Value} ㎍/㎥"
                        val grade = response.body()?.response?.body?.items?.get(0)?.pm10Grade
                        when(grade){
                            "1" -> {
                                tvDustState.text = "좋음"
                                tvDustState.setTextColor(Color.parseColor("#1CAA02"))
                                icStatus.setImageResource(R.drawable.happy)
                            }
                            "2" -> {
                                tvDustState.text = "보통"
                                tvDustState.setTextColor(Color.parseColor("#FF000000"))
                                icStatus.setImageResource(R.drawable.neutral)
                            }
                            "3" -> {
                                tvDustState.text = "나쁨"
                                tvDustState.setTextColor(Color.parseColor("#D55E05"))
                                icStatus.setImageResource(R.drawable.anger)
                            }
                            "4" -> {
                                tvDustState.text = "매우나쁨"
                                tvDustState.setTextColor(Color.parseColor("#FA0032"))
                                icStatus.setImageResource(R.drawable.dead)
                            }
                        }
                    }
                }else{
                    Log.d("TAG", response.message().toString())
                }
            }
        }
        return binding.root
    }
}