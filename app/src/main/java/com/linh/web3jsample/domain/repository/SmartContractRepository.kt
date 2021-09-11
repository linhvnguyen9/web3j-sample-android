package com.linh.web3jsample.domain.repository

import com.linh.web3jsample.domain.entity.Trade
import com.linh.web3jsample.domain.entity.Wallet

interface SmartContractRepository {
    fun getContractAddress(): String
    suspend fun createWallet(password: String) : Wallet
    suspend fun getOwnerAddress(tokenId: Long) : String
    suspend fun getEthBalance(): String
    suspend fun getBalanceOf(address: String): Long
    suspend fun getOwnedTokenIdByAddress(address: String): List<Long>
    suspend fun approveForTrade(tokenId: Long)
    suspend fun estimateGasApproveForTrade(tokenId: Long): String
    suspend fun openTrade(tokenId: Long, priceInEth: String)
    suspend fun estimateGasOpenTrade(tokenId: Long, priceInEth: String): String
    suspend fun getApprovalForTrade(tokenId: Long): Boolean
    suspend fun getTradeForToken(tokenId: Long): Trade
    fun getWallet() : Wallet
}