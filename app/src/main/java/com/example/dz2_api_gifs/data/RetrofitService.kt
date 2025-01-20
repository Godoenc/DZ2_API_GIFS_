package com.example.dz2_api_gifs.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "https://api.giphy.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val giphyApi: GiphyAPI = retrofit.create(GiphyAPI::class.java)
}