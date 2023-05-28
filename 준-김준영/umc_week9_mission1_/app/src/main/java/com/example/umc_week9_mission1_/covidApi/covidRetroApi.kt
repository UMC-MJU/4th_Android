package com.example.umc_week9_mission1_.covidApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory


object covidRetroApi {
    private val BASE_URL = "http://apis.data.go.kr/1790387/covid19CurrentStatusKorea/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    val moshi: Moshi by lazy {
        Moshi.Builder()
            .build()
    }
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    val covidService: CovidService by lazy {
        retrofit.create(CovidService::class.java)
    }
}