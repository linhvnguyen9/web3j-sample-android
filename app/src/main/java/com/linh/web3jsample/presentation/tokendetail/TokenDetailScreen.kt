package com.linh.web3jsample.presentation.tokendetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.presentation.home.TokenItem

@Composable
fun TokenDetailScreen(tokenDetailViewModel: TokenDetailViewModel, tokenId: Long) {
    val token = tokenDetailViewModel.tokenDetail.collectAsState()

    LaunchedEffect(tokenId) {
        tokenDetailViewModel.getTokenDetail(tokenId)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = rememberImagePainter(token.value.imageUrl, builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_image_24)
                }),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
        }
        Text(text = token.value.name, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface)
        Text(token.value.description, style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
        Text("Owner: ${token.value.tokenOwnerAddress}", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
    }
}