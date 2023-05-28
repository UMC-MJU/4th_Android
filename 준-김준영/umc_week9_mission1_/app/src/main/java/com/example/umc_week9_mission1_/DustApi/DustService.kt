package com.example.umc_week9_mission1_.DustApi

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DustService {
    @GET("getMsrstnAcctoRltmMesureDnsty")
    suspend fun getDust(
        @Query("serviceKey") serviceKey:String,
        @Query("stationName") stationName:String,
        @Query("dataTerm") dataTerm:String,
        @Query("returnType") returnType:String
    ): Response<dustInfo>
}