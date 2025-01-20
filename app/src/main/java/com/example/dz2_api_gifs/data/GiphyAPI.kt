package com.example.dz2_api_gifs.data

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g"
    ): DataGiphy
}