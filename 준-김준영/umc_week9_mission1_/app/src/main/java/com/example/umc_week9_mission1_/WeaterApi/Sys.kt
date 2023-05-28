package com.example.umc_week9_mission1_.WeaterApi


import com.squareup.moshi.Json

data class Sys(
    @field:Json(name = "country")
    val country: String?,
    @field:Json(name = "sunrise")
    val sunrise: Int?,
    @field:Json(name = "sunset")
    val sunset: Int?
)