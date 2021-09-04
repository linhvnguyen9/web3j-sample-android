package com.linh.web3jsample.data.contract

import android.app.Application
import com.linh.web3jsample.data.contract.TradeContractService.Companion.TRADE_CONTRACT_ADDRESS
import com.linh.web3jsample.domain.entity.Trade
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

class TradeContractService(private val web3j: Web3j, private val application: Application) {
    private lateinit var smartContract : Classifieds

    fun getContractAddress() : String {
        return smartContract.contractAddress
    }

    fun getTradeByItemId(itemId: Long): Trade {
        return smartContract.getTradeByItem(BigInteger(itemId.toString())).send().run {
            Trade(poster, item.toLong(), price.toString(), status.toString())
        }
    }

    fun initSmartContract(credentials: Credentials) : Classifieds {
        if (!this::smartContract.isInitialized) {
            smartContract = Classifieds.load(
                TRADE_CONTRACT_ADDRESS, web3j, credentials, object :
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

    companion object {
        const val TRADE_CONTRACT_ADDRESS =
            "0xa25280b7daaa988d0b6e779d91b6e36729d4ffff"

        private val ETH_DECIMALS = BigInteger("1000000000000000000")
    }
}