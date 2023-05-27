package com.example.umc_week9

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmgMedService {
    @GET("EmgMedInfo")
    fun getEmgMedData(@Query("KEY") KEY: String,
                      @Query("Type") Type: String): Call<EmgMedResponse>

    @GET("EmgMedInfo")
    suspend fun getDataCoroutine(
        @Query("KEY") KEY : String,
        @Query("Type") Type : String
    ): Response<EmgMedResponse> // 코루틴이 자체적으로 비동기적으로 처리하기때문에 Call은 필요가없다
}
