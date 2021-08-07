package com.linh.web3jsample.data.di

import com.linh.web3jsample.data.contract.HelloWorld
import com.linh.web3jsample.data.contract.SmartContractService
import com.linh.web3jsample.data.repository.SmartContractRepositoryImpl
import com.linh.web3jsample.domain.repository.SmartContractRepository
import org.koin.dsl.module
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

val dataModule = module {
    single { Web3j.build(HttpService("http://192.168.1.109:8545")) }

    single { Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9") }

    single {
        HelloWorld.load(
            "0xe852CCe1988492e1Fc24A7547e01Cf9C9b495Fd6",
            get(),
            get<Credentials>(),
            BigInteger("20000000000", 10),
            BigInteger("6721975", 10)
        )
    }

    factory { SmartContractService(get()) }

    factory { SmartContractRepositoryImpl(get()) as SmartContractRepository }
}