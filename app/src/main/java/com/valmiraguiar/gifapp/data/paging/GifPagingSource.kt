package com.valmiraguiar.gifapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.data.datasource.GifRemoteDataSource
import com.valmiraguiar.gifapp.data.remote.mapper.toModel
import javax.inject.Inject

class GifPagingSource @Inject constructor(
    private val network: GifRemoteDataSource,
) : PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return try {
            val offset = params.key ?: ZERO
            val response = network.getTrendingGifs(
                limit = params.loadSize,
                offset = offset
            )
            val gifs = response.data.toModel()
            val nextKey = response.pagination.offset + response.pagination.count

            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == ZERO) null else maxOf(offset - params.loadSize, ZERO),
                nextKey = if (gifs.isEmpty() || nextKey >= response.pagination.totalCount) {
                    null
                } else {
                    nextKey
                }
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition
        val anchorPage = anchorPosition?.let(state::closestPageToPosition)
        val refreshKey = anchorPage?.prevKey?.plus(state.config.pageSize)
            ?: anchorPage?.nextKey?.minus(state.config.pageSize)

        return refreshKey
    }

    private companion object {
        const val ZERO = 0
    }
}
