package com.linh.web3jsample.data.contract

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.linh.web3jsample.domain.entity.Token
import timber.log.Timber

class TokensPagingSource(
    private val query: String,
    private val service: TokenMetadataService
) : PagingSource<Int, Token>() {
    override fun getRefreshKey(state: PagingState<Int, Token>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Token> {
        return try {
            Timber.d("TokensPagingSource query $query")
            val nextPageNumber = params.key ?: 1
            val response = service.getAllTokensMetadata(query, nextPageNumber, PAGE_SIZE)
            val nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            LoadResult.Page(
                response.map { it.toModel() },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}