package com.linh.web3jsample.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmTransactionDialog(transactionName: String, gasCost: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(onDismissRequest = { onDismiss() }, buttons = {
        Row(Modifier.padding(start = 28.dp, end = 28.dp, bottom = 28.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { onConfirm() }) {
                Text("Confirm")
            }
        }
    }, title = {
        Text("Confirm your transaction")
    }, text = {
        Column {
            Text("Transaction name: $transactionName")
            Text("Gas cost : $gasCost wei")
        }
    })
}