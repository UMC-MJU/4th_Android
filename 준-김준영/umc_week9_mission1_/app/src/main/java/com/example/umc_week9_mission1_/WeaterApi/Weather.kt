package com.example.umc_week9_mission1_.WeaterApi


import com.squareup.moshi.Json

data class Weather(
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "icon")
    val icon: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "main")
    val main: String?
)