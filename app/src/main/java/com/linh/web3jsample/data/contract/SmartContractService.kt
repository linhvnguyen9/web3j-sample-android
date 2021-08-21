package com.linh.web3jsample.data.contract

import android.app.Application
import androidx.compose.runtime.key
import com.linh.web3jsample.Web3JSampleApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet
import org.web3j.crypto.WalletUtils
import timber.log.Timber
import java.io.File

class SmartContractService(private val application: Application, private val smartContract: NonFungibleToken4) {
    fun getContractAddress() : String {
        return smartContract.contractAddress
    }

    /*
    @returns String is wallet address
     */
    suspend fun createWallet(password: String) : LoadWalletResponse = withContext(Dispatchers.IO) {
        val wallet = WalletUtils.generateBip39Wallet(password, application.filesDir)
        return@withContext loadWallet(password, wallet.mnemonic)
    }

    fun loadWallet(password: String, mnemonic: String) : LoadWalletResponse {
        val credential = WalletUtils.loadBip39Credentials(password, mnemonic)
        return LoadWalletResponse(mnemonic, getPrivateKey(credential), getAddress(credential))
    }

    private fun getPrivateKey(credentials: Credentials) : String {
        return credentials.ecKeyPair.privateKey.toString(16)
    }

    private fun getAddress(credentials: Credentials) : String {
        return credentials.address
    }
}