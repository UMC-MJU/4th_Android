package com.example.umc_week9_mission1_.covidApi


import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "result")
    val result: List<Result?>?,
    @field:Json(name = "resultCnt")
    val resultCnt: String?,
    @field:Json(name = "resultCode")
    val resultCode: String?,
    @field:Json(name = "resultMsg")
    val resultMsg: String?
)