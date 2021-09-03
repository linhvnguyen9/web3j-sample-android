package com.linh.web3jsample.data.repository

import com.linh.web3jsample.data.contract.TokenMetadataService
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.repository.TokenRepository
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val tokenMetadataService: TokenMetadataService) : TokenRepository {
    override suspend fun getAllTokenMetadata(): List<Token> {
        return tokenMetadataService.getAllTokensMetadata().map { it.toModel() }
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