package com.valmiraguiar.core.data.network.remote

import com.valmiraguiar.core.data.network.response.RandomGifListResponse
import retrofit2.http.GET

interface GifApi {

    @GET("/v1/gifs/random")
    suspend fun getRandomGifs(): RandomGifListResponse
}