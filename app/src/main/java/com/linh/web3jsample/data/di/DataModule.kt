package com.linh.web3jsample.data.di

import com.linh.web3jsample.data.contract.NonFungibleToken4
import com.linh.web3jsample.data.contract.SmartContractService
import com.linh.web3jsample.data.repository.SmartContractRepositoryImpl
import com.linh.web3jsample.domain.repository.SmartContractRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

val dataModule = module {
    single { Web3j.build(HttpService("http://192.168.1.109:8545")) }

    single { Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9") }

    single {
        NonFungibleToken4.load(
            "0x6aec55c34fcd7f874237becb83e2a2671caa06b9", get(), get<Credentials>(), object :
                ContractGasProvider {
                override fun getGasPrice(contractFunc: String?): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }

                override fun getGasPrice(): BigInteger {
                    return get<Web3j>().ethGasPrice().send().gasPrice
                }

                override fun getGasLimit(contractFunc: String?): BigInteger {
                    return get<Web3j>().ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
                }

                override fun getGasLimit(): BigInteger {
                    return get<Web3j>().ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
                }
            })
    }

    factory { SmartContractService(androidApplication(), get()) }

    factory { SmartContractRepositoryImpl(get()) as SmartContractRepository }
}