package com.linh.web3jsample.domain.repository

import com.linh.web3jsample.domain.entity.Wallet

interface SmartContractRepository {
    fun getContractAddress(): String
    suspend fun createWallet(password: String) : Wallet
    suspend fun getOwnerAddress(tokenId: Long) : String
    suspend fun getEthBalance(): String
    suspend fun getBalanceOf(address: String): Long
    suspend fun getOwnedTokenIdByAddress(address: String): List<Long>
    fun getWallet() : Wallet
}