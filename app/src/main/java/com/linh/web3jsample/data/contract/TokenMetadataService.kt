package com.linh.web3jsample.data.contract

import com.linh.web3jsample.data.response.TokenResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TokenMetadataService {
    @GET("linhvnguyen9/nft-server-test/nfts/")
    suspend fun getAllTokensMetadata() : List<TokenResponse>

    @GET("linhvnguyen9/nft-server-test/nfts/{id}")
    suspend fun getTokenMetadata(@Path("id") id: Long) : TokenResponse

    @GET("linhvnguyen9/nft-server-test/nfts")
    suspend fun getTokenByQuery(@Query("", encoded = true) query: String) : List<TokenResponse>
}