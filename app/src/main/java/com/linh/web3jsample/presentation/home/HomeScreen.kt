package com.linh.web3jsample.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.presentation.common.TokensList
import timber.log.Timber

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val tokens = viewModel.tokens.collectAsState()
    Column {
        TokensList(tokens.value, onClick = { tokenId ->
            viewModel.onClickToken(tokenId)
        })
    }
}