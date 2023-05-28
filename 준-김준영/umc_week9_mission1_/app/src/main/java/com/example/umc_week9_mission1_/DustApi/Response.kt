package com.example.umc_week9_mission1_.DustApi


import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "body")
    val body: Body?,
    @field:Json(name = "header")
    val header: Header?
)