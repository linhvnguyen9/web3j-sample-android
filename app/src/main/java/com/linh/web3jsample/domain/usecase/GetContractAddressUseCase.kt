package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository

class GetContractAddressUseCase(private val repository: SmartContractRepository) {
    operator fun invoke() = repository.getContractAddress()
}