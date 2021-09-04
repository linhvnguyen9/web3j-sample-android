package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenDetailUseCase @Inject constructor(
    private val smartContractRepository: SmartContractRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(tokenId: Long): Token {
        val ownerAddress = smartContractRepository.getOwnerAddress(tokenId)
        val isApprovedForTrade = smartContractRepository.getApprovalForTrade(tokenId)
        val tokenMetadata = tokenRepository.getTokenMetadata(tokenId)

        return tokenMetadata.run {
            Token(id, name, description, imageUrl, ownerAddress, isApprovedForTrade)
        }
    }
}