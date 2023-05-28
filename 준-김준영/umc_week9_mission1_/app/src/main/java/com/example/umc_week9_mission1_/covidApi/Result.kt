package com.example.umc_week9_mission1_.covidApi


import com.squareup.moshi.Json

data class Result(
    @field:Json(name = "cnt_confirmations")
    val cntConfirmations: String?,
    @field:Json(name = "cnt_deaths")
    val cntDeaths: String?,
    @field:Json(name = "cnt_hospitalizations")
    val cntHospitalizations: String?,
    @field:Json(name = "cnt_severe_symptoms")
    val cntSevereSymptoms: String?,
    @field:Json(name = "mmddhh")
    val mmddhh: String?,
    @field:Json(name = "rate_confirmations")
    val rateConfirmations: String?,
    @field:Json(name = "rate_deaths")
    val rateDeaths: String?,
    @field:Json(name = "rate_hospitalizations")
    val rateHospitalizations: String?,
    @field:Json(name = "rate_severe_symptoms")
    val rateSevereSymptoms: String?
)