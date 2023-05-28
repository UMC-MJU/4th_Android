package com.example.umc_week9_mission1_.DustApi


import com.squareup.moshi.Json

data class Header(
    @field:Json(name = "resultCode")
    val resultCode: String?,
    @field:Json(name = "resultMsg")
    val resultMsg: String?
)