package com.example.umc_week9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week9.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val emgAdapter by lazy {
        EmgMedAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = emgAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL)) // 항목마다 줄을 그어주는 것
        }

        binding.btnGet.setOnClickListener {
            retrofitWork()
        }
    }

    private fun retrofitWork(){
        val service = RetrofitApi.emgMedService

        /*service.getEmgMedData(getString((R.string.api_key)), "json")
            .enqueue(object : Callback<EmgMedResponse>{
                override fun onResponse(
                    call: Call<EmgMedResponse>,
                    response: Response<EmgMedResponse>
                ) {
                    if(response.isSuccessful){
                        //Log.d("TAG", response.body().toString())
                        val result = response.body()?.emgMedInfo?.get(1)?.row // NULL 값이 있을 수 있다.
                        emgAdapter.submitList(result!!)
                    }
                }

                override fun onFailure(call: Call<EmgMedResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }

            })*/
        //코루틴으로 작동해보기
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getDataCoroutine(getString(R.string.api_key), "json")

            withContext(Dispatchers.Main){ // UI변경은 Main스레드에서만 가능!
                if(response.isSuccessful){
                    val result = response.body()?.emgMedInfo?.get(1)?.row
                    result?.let {
                        emgAdapter.submitList(it)
                    }
                }else{
                    Log.d("TAG", response.code().toString())
                }
            }
        }

    }



}