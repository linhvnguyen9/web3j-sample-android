package com.linh.web3jsample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linh.web3jsample.data.contract.TokenMetadataService
import com.linh.web3jsample.data.contract.TokensPagingSource
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val tokenMetadataService: TokenMetadataService) : TokenRepository {
    override fun getAllTokenMetadata(filter: String): Flow<PagingData<Token>> {
        Timber.d("getAllTokenMetadata filter $filter")
        return Pager(PagingConfig(pageSize = TokensPagingSource.PAGE_SIZE, enablePlaceholders = true)) {
            TokensPagingSource(filter, tokenMetadataService)
        }.flow
    }

    override suspend fun getTokenMetadata(id: Long) : Token {
        return tokenMetadataService.getTokenMetadata(id).toModel()
    }

    override suspend fun getTokenMetadata(ids: List<Long>): List<Token> {
        val queryString = ids.map { "id=$it" }.joinToString("&")
        Timber.d("getTokenMetadata queryString $queryString ids $ids")
        return tokenMetadataService.getTokenByQuery(queryString).map { it.toModel() }
    }
}