package com.valmiraguiar.gifapp.framework.network.remote

import com.valmiraguiar.core.network.response.TrendingGifListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("/v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TrendingGifListResponse
}
