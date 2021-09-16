package com.linh.web3jsample.data.contract

import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthEstimateGas
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger
import java.util.*
import javax.inject.Inject

class ClassifiedsExtension (credentials: Credentials, web3j: Web3j, contractGasProvider: ContractGasProvider) :
    Classifieds(TRADE_CONTRACT_ADDRESS, web3j, credentials, contractGasProvider) {

    fun estimateGasOpenTrade(
        _item: BigInteger?,
        _price: BigInteger?
    ): Request<*, EthEstimateGas> {
        val function = Function(
            FUNC_OPENTRADE,
            Arrays.asList<Type<*>>(
                Uint256(_item),
                Uint256(_price)
            ), emptyList()
        )

        return web3j.ethEstimateGas(
            Transaction.createFunctionCallTransaction(
                transactionManager.fromAddress,
                BigInteger("1"),
                gasProvider.getGasPrice(NonFungibleToken4.FUNC_APPROVE),
                gasProvider.getGasLimit(NonFungibleToken4.FUNC_APPROVE),
                contractAddress,
                executeRemoteCallTransaction(function).encodeFunctionCall()
            )
        )
    }

    fun estimateGasExecuteTrade(_trade: BigInteger?, value: BigInteger): Request<*, EthEstimateGas> {
        val function = Function(
            FUNC_EXECUTETRADE,
            Arrays.asList<Type<*>>(Uint256(_trade)), emptyList()
        )

        return web3j.ethEstimateGas(
            Transaction.createFunctionCallTransaction(
                transactionManager.fromAddress,
                BigInteger("1"),
                gasProvider.getGasPrice(FUNC_EXECUTETRADE),
                gasProvider.getGasLimit(FUNC_EXECUTETRADE),
                contractAddress,
                value,
                executeRemoteCallTransaction(function).encodeFunctionCall()
            )
        )
    }

    fun executeTrade(
        _trade: BigInteger?,
        value: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt?>? {
        val function = Function(
            FUNC_EXECUTETRADE,
            Arrays.asList<Type<*>>(Uint256(_trade)), emptyList()
        )
        return executeRemoteCallTransaction(function, value)
    }

    fun estimateGasCancelTrade(_trade: BigInteger): Request<*, EthEstimateGas> {
        val function = Function(
            FUNC_CANCELTRADE,
            Arrays.asList<Type<*>>(Uint256(_trade)), emptyList()
        )

        return web3j.ethEstimateGas(
            Transaction.createFunctionCallTransaction(
                transactionManager.fromAddress,
                BigInteger("1"),
                gasProvider.getGasPrice(FUNC_CANCELTRADE),
                gasProvider.getGasLimit(FUNC_CANCELTRADE),
                contractAddress,
                executeRemoteCallTransaction(function).encodeFunctionCall()
            )
        )
    }

    companion object {
        const val TRADE_CONTRACT_ADDRESS =
            "0x69f8aeb7597b9f7d890c261045cb0db0c651aabf"
    }
}