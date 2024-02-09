package com.example.alternativuniversnrk

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("direkte/response.json")
    suspend fun getLiveItems(): LiveResponse
}