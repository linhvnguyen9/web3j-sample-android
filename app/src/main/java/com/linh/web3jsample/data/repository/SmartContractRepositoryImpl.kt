package com.linh.web3jsample.data.repository

import com.linh.web3jsample.data.contract.TokenContractService
import com.linh.web3jsample.data.contract.TradeContractService
import com.linh.web3jsample.data.local.EncryptedSharedPreference
import com.linh.web3jsample.domain.entity.Trade
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class SmartContractRepositoryImpl @Inject constructor(
    private val tokenContractService: TokenContractService,
    private val tradeContractService: TradeContractService
) : SmartContractRepository {
    override fun getContractAddress(): String {
        return tokenContractService.getContractAddress()
    }

    override suspend fun getOwnerAddress(tokenId: Long): String {
        return tokenContractService.getOwnerAddress(tokenId)
    }

    override suspend fun createWallet(password: String): Wallet {
        val createdWallet = tokenContractService.createWallet(password).toWallet()

        createdWallet.run {
            EncryptedSharedPreference.saveWalletAddress(address)
            EncryptedSharedPreference.saveWalletMnemonic(mnemonic)
            EncryptedSharedPreference.saveWalletPassword(password)
            EncryptedSharedPreference.saveWalletPrivateKey(privateKey)
        }

        return createdWallet
    }

    override suspend fun getEthBalance(): String {
        val wallet = getWallet()
        return tokenContractService.getEthBalance(wallet.address)
    }

    override suspend fun getBalanceOf(address: String): Long {
        return tokenContractService.getBalanceOf(address)
    }

    override suspend fun getOwnedTokenIdByAddress(address: String): List<Long> {
        val balance = tokenContractService.getBalanceOf(address)
        val result = mutableListOf<Long>()
        for (i in 0 until balance) {
            val tokenId = tokenContractService.getTokenOfOwnerByIndex(address, i)
            result.add(tokenId)
        }
        return result
    }

    override suspend fun approveForTrade(tokenId: Long) {
        tokenContractService.approve(TradeContractService.TRADE_CONTRACT_ADDRESS, tokenId)
    }

    override suspend fun estimateGasApproveForTrade(tokenId: Long): String {
        return tokenContractService.estimateGasApprove(TradeContractService.TRADE_CONTRACT_ADDRESS, tokenId)
    }

    override suspend fun getApprovalForTrade(tokenId: Long): Boolean {
        return tokenContractService.getApprovedAddress(tokenId) == TradeContractService.TRADE_CONTRACT_ADDRESS
    }

    override suspend fun getTradeForToken(tokenId: Long) : Trade {
        return tradeContractService.getTradeByItemId(tokenId)
    }

    override fun getWallet(): Wallet {
        val address = EncryptedSharedPreference.getWalletAddress()
        val mnemonic = EncryptedSharedPreference.getWalletMnemonic()
        val privateKey = EncryptedSharedPreference.getWalletPrivateKey()
        val password = EncryptedSharedPreference.getWalletPassword()

        tokenContractService.loadWallet(password, mnemonic)

        val credentials = tokenContractService.getCredentials(password, mnemonic)
        tradeContractService.initSmartContract(credentials)

        return Wallet(address, mnemonic, privateKey)
    }
}