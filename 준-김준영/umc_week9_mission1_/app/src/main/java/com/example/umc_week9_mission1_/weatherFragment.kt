package com.example.umc_week9_mission1_

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_week9_mission1_.WeaterApi.RetrofitApi
import com.example.umc_week9_mission1_.databinding.WeatherFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class weatherFragment : Fragment() {
    private val binding : WeatherFragmentBinding by lazy {
        WeatherFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val service = RetrofitApi.weatherService

        CoroutineScope(Dispatchers.IO).launch{
            val response = service.getWeather(37.541, 126.986, getString(R.string.api_key))
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    with(binding){
                        tvMain.text = "현재 날씨 : ${response.body()?.weather?.get(0)?.main}"
                        val currentTemp = response.body()?.main?.temp?.minus(273.15)?.toInt()
                        tvDe.text = "현재 온도 : ${currentTemp.toString()}°C"
                        when(response.body()?.weather?.get(0)?.icon){
                            "01d"->{
                                icWeather.setImageResource(R.drawable.sun)
                            }
                            "02d"->{
                                icWeather.setImageResource(R.drawable.cloudy)
                            }
                            "03d"->{
                                icWeather.setImageResource(R.drawable.cloud)
                            }
                            "10d"->{
                                icWeather.setImageResource(R.drawable.rain)
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