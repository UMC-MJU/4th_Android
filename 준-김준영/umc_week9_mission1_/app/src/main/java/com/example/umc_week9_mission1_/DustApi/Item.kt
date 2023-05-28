package com.example.umc_week9_mission1_.DustApi


import com.squareup.moshi.Json

data class Item(
    @field:Json(name = "coFlag")
    val coFlag: Any?,
    @field:Json(name = "coGrade")
    val coGrade: String?,
    @field:Json(name = "coValue")
    val coValue: String?,
    @field:Json(name = "dataTime")
    val dataTime: String?,
    @field:Json(name = "khaiGrade")
    val khaiGrade: String?,
    @field:Json(name = "khaiValue")
    val khaiValue: String?,
    @field:Json(name = "no2Flag")
    val no2Flag: Any?,
    @field:Json(name = "no2Grade")
    val no2Grade: String?,
    @field:Json(name = "no2Value")
    val no2Value: String?,
    @field:Json(name = "o3Flag")
    val o3Flag: Any?,
    @field:Json(name = "o3Grade")
    val o3Grade: String?,
    @field:Json(name = "o3Value")
    val o3Value: String?,
    @field:Json(name = "pm10Flag")
    val pm10Flag: Any?,
    @field:Json(name = "pm10Grade")
    val pm10Grade: String?,
    @field:Json(name = "pm10Value")
    val pm10Value: String?,
    @field:Json(name = "so2Flag")
    val so2Flag: Any?,
    @field:Json(name = "so2Grade")
    val so2Grade: String?,
    @field:Json(name = "so2Value")
    val so2Value: String?
)