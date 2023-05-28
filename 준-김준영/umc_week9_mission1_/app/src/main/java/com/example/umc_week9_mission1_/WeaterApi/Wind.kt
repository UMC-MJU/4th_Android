package com.example.umc_week9_mission1_.WeaterApi


import com.squareup.moshi.Json

data class Wind(
    @field:Json(name = "deg")
    val deg: Int?,
    @field:Json(name = "gust")
    val gust: Double?,
    @field:Json(name = "speed")
    val speed: Double?
)