package com.linh.web3jsample.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.utils.extensions.isNull
import timber.log.Timber

@Composable
fun TokensList(tokens: LazyPagingItems<Token>, onClick: (tokenId: Long) -> Unit) {
    LazyColumn {
        if (tokens.loadState.refresh == LoadState.Loading) {
            item {
                PlaceholderTokenItems()
            }
        }

        items(tokens.itemCount) { index ->
            val currentToken = tokens[index]
            Timber.d("TokensList current token $currentToken")
            TokenItem(currentToken) {
                currentToken?.let {
                    onClick(it.id)
                }
            }
        }

        if (tokens.loadState.append == LoadState.Loading) {
            item {
                PlaceholderTokenItems()
            }
        }
    }
}

@Composable
fun TokensList(tokens: List<Token>, onClick: (tokenId: Long) -> Unit) {
    LazyColumn {
        if (tokens.isEmpty()) {
            item {
                PlaceholderTokenItems()
            }
        } else {
            itemsIndexed(tokens) { index: Int, token: Token ->
                TokenItem(token) {
                    onClick(token.id)
                }
            }
        }
    }
}

@Composable
fun TokenItem(token: Token?, onClick: () -> Unit) {
    val placeholderModifier = Modifier.placeholder(
        token.isNull(),
        highlight = PlaceholderHighlight.fade()
    )

    Card(Modifier
        .clickable {
            onClick()
        }
    ) {
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
                    painter = rememberImagePainter(token?.imageUrl, builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_baseline_image_24)
                    }),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                        .then(placeholderModifier)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = token?.name ?: "",
                Modifier
                    .fillMaxWidth()
                    .then(placeholderModifier),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))
            Text(
                token?.description ?: "",
                Modifier
                    .fillMaxWidth()
                    .then(placeholderModifier),
                style = MaterialTheme.typography.body1
            )
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun LazyItemScope.PlaceholderTokenItems(count: Int = 5) {
    repeat(count) {
        TokenItem(null) { }
    }
}

@Preview
@Composable
fun TokenItemPreview() {
    TokenItem(Token(0, "My first token", "This is the first token", "", ""), {})
}