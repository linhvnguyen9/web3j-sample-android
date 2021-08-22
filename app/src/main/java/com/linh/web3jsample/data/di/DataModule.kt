package com.linh.web3jsample.data.di

import android.app.Application
import com.linh.web3jsample.data.contract.NonFungibleToken4
import com.linh.web3jsample.data.contract.SmartContractService
import com.linh.web3jsample.data.repository.SmartContractRepositoryImpl
import com.linh.web3jsample.domain.repository.SmartContractRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideWeb3j() : Web3j {
        return Web3j.build(HttpService("http://192.168.1.109:8545"))
    }

    @Provides
    fun provideCredentials() : Credentials {
        return Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
    }

    @Provides
    fun provideSmartContract(web3j: Web3j, credentials: Credentials) : NonFungibleToken4 {
        return NonFungibleToken4.load(
            "0x6aec55c34fcd7f874237becb83e2a2671caa06b9", web3j, credentials, object :
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

    @Provides
    fun provideSmartContractService(application: Application, smartContract: NonFungibleToken4) : SmartContractService {
        return SmartContractService(application, smartContract)
    }
}