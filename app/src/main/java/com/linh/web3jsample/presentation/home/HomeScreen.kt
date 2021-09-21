package com.linh.web3jsample.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.presentation.common.TokensList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val tokens = viewModel.tokens.collectAsLazyPagingItems()
    val query = viewModel.query.collectAsState()

    Column {
        OutlinedTextField(
            value = query.value,
            onValueChange = { viewModel.setQuery(it) },
            label = { Text("Search tokens") },
            keyboardOptions = KeyboardOptions(imeAction = Search),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        TokensList(tokens, onClick = { tokenId ->
            viewModel.onClickToken(tokenId)
        })
    }
}