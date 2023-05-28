package com.example.umc_week9_mission1_

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_week9_mission1_.covidApi.covidRetroApi
import com.example.umc_week9_mission1_.databinding.CovidFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class covidFragment : Fragment() {
    private val binding: CovidFragmentBinding by lazy {
        CovidFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val service = covidRetroApi.covidService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getCovid(getString(R.string.covid_api_key))
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    with(binding){
                        tvCurrentStatus.text = "일일 확진자 수 : ${response.body()?.response?.result?.get(0)?.cntConfirmations}"
                        tvStatusDanger.text = "일일 중증 환자 수: ${response.body()?.response?.result?.get(0)?.cntSevereSymptoms}"
                    }
                }else{
                    Log.d("TAG", response.message().toString())
                }
            }
        }
        return binding.root
    }
}