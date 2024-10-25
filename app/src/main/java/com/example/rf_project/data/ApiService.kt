package com.example.rf_project.data


import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getData(@Url url: String): ResponseData
}