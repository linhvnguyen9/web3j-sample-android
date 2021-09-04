package com.linh.web3jsample.domain.entity

data class Token(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val tokenOwnerAddress: String,
    val isTradeApproved: Boolean = false
)
