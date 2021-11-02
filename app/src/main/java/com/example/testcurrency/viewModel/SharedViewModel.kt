package com.example.testcurrency.viewModel

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcurrency.data.CurrencyResult


class SharedViewModel : ViewModel() {
    private val selected = MutableLiveData<ClipData.Item>()

    val message = MutableLiveData<CurrencyResult>()

    fun sendMessage(currency: CurrencyResult) {
        message.value = currency
    }
}

