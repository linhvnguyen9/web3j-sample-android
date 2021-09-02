package com.linh.web3jsample.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.linh.web3jsample.domain.entity.Wallet

@Composable
fun WalletScreen(
    viewModel: WalletViewModel
) {
    val ethBalance = viewModel.ethBalance.collectAsState()
    val wallet = viewModel.wallet.collectAsState(Wallet("", "", ""))

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("ETH balance ${ethBalance.value}", color = MaterialTheme.colors.onSurface)
        Text("Wallet address: ${wallet.value.address}", color = MaterialTheme.colors.onSurface)
        Text("Wallet mnemonic: ${wallet.value.mnemonic}", color = MaterialTheme.colors.onSurface)
        Text(
            "Wallet private key: ${wallet.value.privateKey}",
            color = MaterialTheme.colors.onSurface
        )
        Text("My NFTs", Modifier.clickable {

        })
    }
}