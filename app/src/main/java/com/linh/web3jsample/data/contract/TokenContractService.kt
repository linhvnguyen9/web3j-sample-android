package com.linh.web3jsample.data.contract

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.tx.gas.ContractGasProvider
import timber.log.Timber
import java.math.BigInteger

class TokenContractService(private val web3j: Web3j, private val application: Application) {
    private lateinit var smartContract: NonFungibleToken4

    fun getContractAddress(): String {
        return smartContract.contractAddress
    }

    /*
    @returns String is wallet address
     */
    suspend fun createWallet(password: String): LoadWalletResponse = withContext(Dispatchers.IO) {
        val wallet = WalletUtils.generateBip39Wallet(password, application.filesDir)
        return@withContext loadWallet(password, wallet.mnemonic)
    }

    fun getCredentials(password: String, mnemonic: String): Credentials {
        return WalletUtils.loadBip39Credentials(password, mnemonic)
    }

    fun loadWallet(password: String, mnemonic: String): LoadWalletResponse {
        val credential = getCredentials(password, mnemonic)
        initSmartContract(credential)
        return LoadWalletResponse(mnemonic, getPrivateKey(credential), getAddress(credential))
    }

    private fun initSmartContract(credentials: Credentials): NonFungibleToken4 {
        if (!this::smartContract.isInitialized) {
            smartContract = NonFungibleToken4.load(
                ERC721_SMART_CONTRACT_ADDRESS, web3j, credentials, object :
                    ContractGasProvider {
                    override fun getGasPrice(contractFunc: String?): BigInteger {
                        return web3j.ethGasPrice().send().gasPrice
                    }

                    override fun getGasPrice(): BigInteger {
                        return web3j.ethGasPrice().send().gasPrice
                    }

                    override fun getGasLimit(contractFunc: String?): BigInteger {
                        return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
                    }

                    override fun getGasLimit(): BigInteger {
                        return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
                    }
                })
        }
        return smartContract
    }

    private fun getPrivateKey(credentials: Credentials): String {
        return credentials.ecKeyPair.privateKey.toString(16)
    }

    private fun getAddress(credentials: Credentials): String {
        return credentials.address
    }

    suspend fun getOwnerAddress(tokenId: Long): String =
        withContext(Dispatchers.IO) {
            smartContract.ownerOf(BigInteger(tokenId.toString())).send()
        }

    suspend fun getEthBalance(address: String): String = withContext(Dispatchers.IO) {
        return@withContext web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
            .send()
            .balance
            .convertToBalanceString()
    }

    suspend fun getBalanceOf(address: String): Long = withContext(Dispatchers.IO) {
        val balance = smartContract.balanceOf(address).send()
        return@withContext balance.toLong()
    }

    suspend fun getTokenOfOwnerByIndex(owner: String, index: Long): Long =
        withContext(Dispatchers.IO) {
            return@withContext smartContract.tokenOfOwnerByIndex(
                owner,
                BigInteger(index.toString())
            ).send().toLong()
        }

    suspend fun approve(to: String, tokenId: Long) {
        withContext(Dispatchers.IO) {
            return@withContext smartContract.approve(to, BigInteger(tokenId.toString()))
        }
    }

    private fun BigInteger.convertToBalanceString(): String {
        val integerPart = this.divide(ETH_DECIMALS)
        val fractionalPart = this.mod(ETH_DECIMALS)
        return "$integerPart.$fractionalPart"
    }

    suspend fun getApprovedAddress(tokenId: Long): String = withContext(Dispatchers.IO) {
        return@withContext smartContract.getApproved(tokenId.toBigInteger()).send()
    }

    companion object {
        private const val ERC721_SMART_CONTRACT_ADDRESS =
            "0x6aec55c34fcd7f874237becb83e2a2671caa06b9"

        private val ETH_DECIMALS = BigInteger("1000000000000000000")
    }
}