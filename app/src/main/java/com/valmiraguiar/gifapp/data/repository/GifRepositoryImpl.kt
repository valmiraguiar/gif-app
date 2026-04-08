package com.valmiraguiar.gifapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.core.domain.repository.GifRepository
import com.valmiraguiar.gifapp.data.datasource.GifRemoteDataSource
import com.valmiraguiar.gifapp.data.paging.GifPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val network: GifRemoteDataSource,
) : GifRepository {

    override fun observeTrendingGifs(): Flow<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(network) }
        ).flow
    }

    private companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
