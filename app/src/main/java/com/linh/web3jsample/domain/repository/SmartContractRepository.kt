package com.linh.web3jsample.domain.repository

import com.linh.web3jsample.domain.entity.Wallet

interface SmartContractRepository {
    fun getContractAddress(): String
    suspend fun createWallet(password: String) : Wallet
    suspend fun getOwnerAddress(tokenId: Long) : String
    fun getWallet() : Wallet
}