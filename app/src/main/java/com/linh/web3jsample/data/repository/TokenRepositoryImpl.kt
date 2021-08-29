package com.linh.web3jsample.data.repository

import com.linh.web3jsample.data.contract.TokenMetadataService
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val tokenMetadataService: TokenMetadataService) : TokenRepository {
    override suspend fun getAllTokenMetadata(): List<Token> {
        return tokenMetadataService.getAllTokensMetadata().map { it.toModel() }
    }

    override fun getTokenMetadata(id: Long) {
        TODO("Not yet implemented")
    }
}