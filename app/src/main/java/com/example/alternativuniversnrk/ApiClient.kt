package com.example.alternativuniversnrk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val PARSE_URL = "https://stnrklivenow.blob.core.windows.net/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(PARSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

