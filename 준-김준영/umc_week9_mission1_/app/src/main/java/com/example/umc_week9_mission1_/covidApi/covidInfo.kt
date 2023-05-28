package com.example.umc_week9_mission1_.covidApi


import com.squareup.moshi.Json

data class covidInfo(
    @Json(name = "response")
    val response: Response?
)