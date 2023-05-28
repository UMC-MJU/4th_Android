package com.example.umc_week9_mission1_.WeaterApi


import com.squareup.moshi.Json

data class Clouds(
    @field:Json(name = "all")
    val all: Int?
)