package com.valmiraguiar.core.domain.usecase

import androidx.paging.PagingData
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.core.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingGifsUseCase(
    private val repository: GifRepository
) {
    operator fun invoke(): Flow<PagingData<Gif>> = repository.observeTrendingGifs()
}
