package com.valmiraguiar.gifapp.data.datasource

import com.valmiraguiar.gifapp.data.remote.dto.TrendingGifListResponse

interface GifRemoteDataSource {
    suspend fun getTrendingGifs(limit: Int, offset: Int): TrendingGifListResponse
}
