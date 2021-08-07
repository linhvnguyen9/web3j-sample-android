package com.linh.web3jsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.usecase.GetContractAddressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val getContractAddressUseCase: GetContractAddressUseCase) : ViewModel() {
    private val _contractAddress = MutableStateFlow("")
    val contractAddress : StateFlow<String>
        get() = _contractAddress

    init {
        getContractAddress()
    }

    private fun getContractAddress() {
        val contractAddress = getContractAddressUseCase()
        _contractAddress.value = contractAddress
    }
}