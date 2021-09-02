package com.linh.web3jsample.data.repository

import com.linh.web3jsample.data.contract.SmartContractService
import com.linh.web3jsample.data.local.EncryptedSharedPreference
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class SmartContractRepositoryImpl @Inject constructor(val service: SmartContractService) : SmartContractRepository {
    override fun getContractAddress(): String {
        return service.getContractAddress()
    }

    override suspend fun getOwnerAddress(tokenId: Long): String {
        return service.getOwnerAddress(tokenId)
    }

    override suspend fun createWallet(password: String) : Wallet {
        val createdWallet = service.createWallet(password).toWallet()

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
        return service.getEthBalance(wallet.address)
    }

    override fun getWallet(): Wallet {
        val address= EncryptedSharedPreference.getWalletAddress()
        val mnemonic = EncryptedSharedPreference.getWalletMnemonic()
        val privateKey = EncryptedSharedPreference.getWalletPrivateKey()
        val password = EncryptedSharedPreference.getWalletPassword()

        service.loadWallet(password, mnemonic)

        return Wallet(address, mnemonic, privateKey)
    }
}