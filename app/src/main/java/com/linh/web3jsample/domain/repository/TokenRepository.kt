package com.linh.web3jsample.domain.repository

import androidx.paging.PagingData
import com.linh.web3jsample.domain.entity.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getAllTokenMetadata(filter: String) : Flow<PagingData<Token>>
    suspend fun getTokenMetadata(id: Long) : Token
    suspend fun getTokenMetadata(ids: List<Long>): List<Token>
}