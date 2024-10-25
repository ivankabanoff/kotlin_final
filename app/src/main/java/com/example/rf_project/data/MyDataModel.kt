package com.example.rf_project.data

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: String
)