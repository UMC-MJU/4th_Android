package com.example.umc_week9_mission1_.DustApi


import com.squareup.moshi.Json

data class Body(
    @field:Json(name = "items")
    val items: List<Item?>?,
    @field:Json(name = "numOfRows")
    val numOfRows: Int?,
    @field:Json(name = "pageNo")
    val pageNo: Int?,
    @field:Json(name = "totalCount")
    val totalCount: Int?
)