package com.linh.web3jsample.domain.repository

import com.linh.web3jsample.domain.entity.Token

interface TokenRepository {
    suspend fun getAllTokenMetadata() : List<Token>
    fun getTokenMetadata(id: Long)
}