package com.linh.web3jsample.data.contract

import com.linh.web3jsample.data.response.TokenResponse
import retrofit2.http.GET

interface TokenMetadataService {
    @GET("linhvnguyen9/nft-server-test/nfts/")
    suspend fun getAllTokensMetadata() : List<TokenResponse>
}