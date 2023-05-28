package com.example.umc_week9_mission1_.DustApi

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object dustRetroApi {
    private val BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"

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

    val dustService: DustService by lazy {
        retrofit.create(DustService::class.java)
    }
}