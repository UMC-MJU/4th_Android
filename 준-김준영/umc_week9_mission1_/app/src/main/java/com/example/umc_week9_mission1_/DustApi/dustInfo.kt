package com.example.umc_week9_mission1_.DustApi


import com.squareup.moshi.Json

data class dustInfo(
    @field:Json(name = "response")
    val response: Response?
)