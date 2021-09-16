package com.linh.web3jsample.presentation.tokendetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.linh.web3jsample.R
import com.linh.web3jsample.domain.entity.Trade
import com.linh.web3jsample.presentation.common.ConfirmTransactionDialog
import com.linh.web3jsample.presentation.entity.Transaction

@Composable
fun TokenDetailScreen(tokenDetailViewModel: TokenDetailViewModel, tokenId: Long) {
    val token = tokenDetailViewModel.tokenDetail.collectAsState()
    val trade = tokenDetailViewModel.trade.collectAsState()
    val transaction = tokenDetailViewModel.transaction.collectAsState()

    val tradePrice = tokenDetailViewModel.tradePrice.collectAsState()

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

        token.value.run {
            Text(text = name, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface)
            Text(description, style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
            Text("Owner: $tokenOwnerAddress", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
            Text("Is trade approved $isTradeApproved", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
            TextButton(onClick = {
                tokenDetailViewModel.approveForTrade()
            }) {
                Text(text = "Approve!")
            }
        }

        Spacer(Modifier.height(16.dp))

        trade.value.run {
            Text("Trade info", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onSurface)
            Divider()
            Spacer(Modifier.height(8.dp))

            if (posterAddress.isBlank()) {
                Text("There are no trade associated with this token.")
                ConstraintLayout(Modifier.fillMaxWidth()) {
                    val (input, unit) = createRefs()
                    TextField(
                        modifier = Modifier.constrainAs(input) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(unit.start, 8.dp)
                            width = Dimension.fillToConstraints
                        },
                        value = tradePrice.value,
                        onValueChange = {
                            tokenDetailViewModel.tradePrice.value = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Enter amount to list")
                        }
                    )
                    Text("wei", Modifier.wrapContentWidth().constrainAs(unit) {
                        end.linkTo(parent.end)
                        top.linkTo(input.top)
                        bottom.linkTo(input.bottom)
                    })
                }
                Button(onClick = {
                    tokenDetailViewModel.onClickCreateTrade()
                }) {
                    Text(text = "Create trade")
                }
            } else {
                Text("Poster's address $posterAddress", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
                Text("Price $price", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
                Text("Status $status", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
                Text("", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface)
                if (!status.contains("Executed")) {
                    Button(onClick = {
                        tokenDetailViewModel.onClickBuy()
                    }) {
                        Text(text = "Buy!")
                    }
                }
            }
        }
    }

    transaction.value?.let {
        val transactionName = when (it.transaction) {
            Transaction.APPROVE_FOR_TRADE -> "Approve for trade"
            Transaction.CREATE_TRADE -> "Create trade"
            Transaction.EXECUTE_TRADE -> "Execute trade"
        }
        ConfirmTransactionDialog(transactionName, it.gasPrice, onConfirm = {
            when (it.transaction) {
                Transaction.APPROVE_FOR_TRADE -> tokenDetailViewModel.confirmApproveForTrade()
                Transaction.CREATE_TRADE -> tokenDetailViewModel.confirmCreateTrade()
                Transaction.EXECUTE_TRADE -> tokenDetailViewModel.confirmExecuteTrade()
            }
        }) {
            tokenDetailViewModel.resetTransaction()
        }
    }
}