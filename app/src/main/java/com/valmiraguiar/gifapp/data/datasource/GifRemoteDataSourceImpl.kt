@file:Suppress("LongMethod, MaximumLineLength, UnusedPrivateProperty")

package com.valmiraguiar.gifapp.data.datasource

import com.valmiraguiar.gifapp.data.remote.api.GifApi
import com.valmiraguiar.gifapp.data.remote.dto.TrendingGifListResponse
import javax.inject.Inject

class GifRemoteDataSourceImpl @Inject constructor(
    private val api: GifApi
) : GifRemoteDataSource {
    override suspend fun getTrendingGifs(
        limit: Int,
        offset: Int
    ): TrendingGifListResponse = api.getTrendingGifs(limit, offset)
}
