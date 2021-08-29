package com.linh.web3jsample.data.response

import com.google.gson.annotations.SerializedName
import com.linh.web3jsample.domain.entity.Token

data class TokenResponse(
    val id: Long?,
    val name: String?,
    val description: String?,
    @SerializedName("image")
    val imageUrl: String?,
) {
    fun toModel() = Token(id ?: 0L, name ?: "", description ?: "", imageUrl ?: "", "")
}