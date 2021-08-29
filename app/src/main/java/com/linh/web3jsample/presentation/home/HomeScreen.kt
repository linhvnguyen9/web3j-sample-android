package com.linh.web3jsample.presentation.home

import androidx.compose.foundation.Image
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
import timber.log.Timber

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val tokens = viewModel.tokens.collectAsState()
    Column {
        val wallet = viewModel.wallet.collectAsState(Wallet("", "", ""))

        Text("Wallet address: ${wallet.value.address}", color = MaterialTheme.colors.onSurface)
        Text("Wallet mnemonic: ${wallet.value.mnemonic}", color = MaterialTheme.colors.onSurface)
        Text(
            "Wallet private key: ${wallet.value.privateKey}",
            color = MaterialTheme.colors.onSurface
        )

        Spacer(Modifier.height(32.dp))

        LazyColumn {
            itemsIndexed(tokens.value) { index: Int, token: Token ->
                TokenItem(token)
            }
        }
    }
}

@Composable
fun TokenItem(token: Token) {
    Timber.d("token $token")
    Card {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box(Modifier.fillMaxWidth().wrapContentHeight()) {
                Image(
                    painter = rememberImagePainter(token.imageUrl, builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_baseline_image_24)
                    }),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp).align(Alignment.Center)
                )
            }
            Text(text = token.name, style = MaterialTheme.typography.h6)
            Text(token.description, style = MaterialTheme.typography.body1)
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Preview
@Composable
fun TokenItemPreview() {
    TokenItem(Token(0, "My first token", "This is the first token", ""))
}