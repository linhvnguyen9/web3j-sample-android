package com.linh.web3jsample.data.contract

import android.app.Application
import com.linh.web3jsample.data.contract.TradeContractService.Companion.TRADE_CONTRACT_ADDRESS
import com.linh.web3jsample.domain.entity.Trade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.tuples.generated.Tuple2
import org.web3j.tx.exceptions.ContractCallException
import org.web3j.tx.gas.ContractGasProvider
import timber.log.Timber
import java.math.BigInteger
import java.nio.charset.StandardCharsets

class TradeContractService(private val web3j: Web3j, private val contractGasProvider: ContractGasProvider) {
    private lateinit var smartContract : ClassifiedsExtension

    suspend fun getTradeByItemId(itemId: Long): Trade =
        withContext(Dispatchers.IO) {
            try {
                return@withContext smartContract.getTradeByItem(BigInteger(itemId.toString()))
                    .send().run {
                        val trade = component2()
                    Trade(component1().toLong(), trade.poster, trade.item.toLong(), trade.price.toString(10), trade.status.decodeToString())
                }
            } catch (e: ContractCallException) {
                Timber.d("getTradeByItemId exception loc message ${e.localizedMessage} cause message${e.cause?.message}")
                return@withContext Trade(0, "", 0L, "", "")
            }
        }

    suspend fun openTrade(tokenId: Long, priceInEth: String) {
        withContext(Dispatchers.IO) {
            try {
                return@withContext smartContract.openTrade(
                    tokenId.toBigInteger(), priceInEth.toBigInteger()).send()
            } catch (e: ContractCallException) {
                Timber.e("Open trade error ${e.localizedMessage}")
            }
        }
    }

    suspend fun estimateGasOpenTrade(tokenId: Long, priceInEth: String): String =
        withContext(Dispatchers.IO) {
            return@withContext smartContract.estimateGasOpenTrade(
                tokenId.toBigInteger(),
                priceInEth.toBigInteger()
            ).send().amountUsed.toString(10)
        }

    suspend fun estimateGasCancelTrade(tradeId: Long): String =
        withContext(Dispatchers.IO) {
            return@withContext smartContract.estimateGasCancelTrade(
                tradeId.toBigInteger(),
            ).send().amountUsed.toString(10)
        }

    suspend fun cancelTrade(tradeId: Long) {
        withContext(Dispatchers.IO) {
            smartContract.cancelTrade(tradeId.toBigInteger()).send()
        }
    }

    suspend fun executeTrade(tradeId: Long, value: String) {
        withContext(Dispatchers.IO) {
            try {
                return@withContext smartContract.executeTrade(
                    tradeId.toBigInteger(),
                    value.toBigInteger()
                )?.send()
            } catch (e: ContractCallException) {
                Timber.e("Execute trade error ${e.localizedMessage}")
            }
        }
    }

    suspend fun estimateGasExecuteTrade(tradeId: Long, value: String): String =
        withContext(Dispatchers.IO) {
            return@withContext smartContract.estimateGasExecuteTrade(
                tradeId.toBigInteger(),
                value.toBigInteger()
            ).send().amountUsed.toString(10)
        }

    fun initSmartContract(credentials: Credentials): Classifieds {
        if (!this::smartContract.isInitialized) {
            smartContract = ClassifiedsExtension(credentials, web3j, contractGasProvider)
        }
        return smartContract
    }

    companion object {
        const val TRADE_CONTRACT_ADDRESS =
            "0x69f8aeb7597b9f7d890c261045cb0db0c651aabf"

        private val ETH_DECIMALS = BigInteger("1000000000000000000")
    }
}