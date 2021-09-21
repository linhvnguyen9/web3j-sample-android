package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import javax.inject.Inject

class GetAllTokensUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(filter: String) = tokenRepository.getAllTokenMetadata(filter)
}