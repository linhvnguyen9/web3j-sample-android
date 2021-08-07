package com.linh.web3jsample.data.repository

import com.linh.web3jsample.data.contract.SmartContractService
import com.linh.web3jsample.domain.repository.SmartContractRepository

class SmartContractRepositoryImpl(val service: SmartContractService) : SmartContractRepository {
    override fun getContractAddress(): String {
        return service.getContractAddress()
    }
}