package com.linh.web3jsample.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.linh.web3jsample.domain.entity.Wallet

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Column {
        val wallet = viewModel.wallet.collectAsState(Wallet("", "", ""))

        Text("Wallet address: ${wallet.value.address}", color = MaterialTheme.colors.onSurface)
        Text("Wallet mnemonic: ${wallet.value.mnemonic}", color = MaterialTheme.colors.onSurface)
        Text("Wallet private key: ${wallet.value.privateKey}", color = MaterialTheme.colors.onSurface)
    }
}