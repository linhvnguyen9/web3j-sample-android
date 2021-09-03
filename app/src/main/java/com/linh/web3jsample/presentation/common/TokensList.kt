package com.linh.web3jsample.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Token
import timber.log.Timber

@Composable
fun TokensList(tokens: List<Token>, onClick: (tokenId: Long) -> Unit) {
    LazyColumn {
        itemsIndexed(tokens) { index: Int, token: Token ->
            TokenItem(token) {
                onClick(token.id)
            }
        }
    }
}

@Composable
fun TokenItem(token: Token, onClick: () -> Unit) {
    Timber.d("token $token")
    Card(Modifier.clickable {
        onClick()
    }) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = rememberImagePainter(token.imageUrl, builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_baseline_image_24)
                    }),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
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
    TokenItem(Token(0, "My first token", "This is the first token", "", ""), {})
}