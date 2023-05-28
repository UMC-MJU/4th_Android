package com.example.umc_week9_mission1_.WeaterApi


import com.squareup.moshi.Json

data class Coord(
    @field:Json(name = "lat")
    val lat: Double?,
    @field:Json(name = "lon")
    val lon: Double?
)