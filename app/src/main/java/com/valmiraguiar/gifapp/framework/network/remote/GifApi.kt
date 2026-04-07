package com.valmiraguiar.gifapp.framework.network.remote

import com.valmiraguiar.gifapp.framework.network.response.RandomGifListResponse
import retrofit2.http.GET

interface GifApi {

    @GET("/v1/gifs/random")
    suspend fun getRandomGifs(): RandomGifListResponse
}
