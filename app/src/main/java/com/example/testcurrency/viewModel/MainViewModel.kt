package com.example.testcurrency.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.testcurrency.repository.CurrencyRepository
import com.example.testcurrency.data.CurrencyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent


@RequiresApi(Build.VERSION_CODES.O)
@KoinApiExtension
class MainViewModel(
    private val cRepository: CurrencyRepository

) : ViewModel(), KoinComponent {

    val currencyLiveDataBd: LiveData<List<CurrencyResult>> = cRepository.getCurrencyBd().asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            cRepository.addCurrency()
        }
    }
}



