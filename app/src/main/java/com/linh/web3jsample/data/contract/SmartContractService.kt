package com.linh.web3jsample.data.contract

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SmartContractService(private val smartContract: HelloWorld) {
    fun getContractAddress() : String {
        return smartContract.contractAddress
    }
}