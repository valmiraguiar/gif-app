package com.valmiraguiar.core.domain.repository

import androidx.paging.PagingData
import com.valmiraguiar.core.domain.model.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
    fun observeTrendingGifs(): Flow<PagingData<Gif>>
}
