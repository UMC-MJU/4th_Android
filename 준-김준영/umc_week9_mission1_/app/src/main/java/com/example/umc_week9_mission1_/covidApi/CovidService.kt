package com.example.umc_week9_mission1_.covidApi

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response


interface CovidService {
    @GET("covid19CurrentStatusKoreaJason")
    suspend fun getCovid(
        @Query("serviceKey") serviceKey:String
    ): Response<covidInfo>
}