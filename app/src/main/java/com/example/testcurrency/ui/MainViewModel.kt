package com.example.characters.screen

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.currency.restApi.CurrencyRepository
import com.example.testcurrency.data.CurrencyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import java.time.LocalDate
import java.time.temporal.ChronoUnit


@RequiresApi(Build.VERSION_CODES.O)
@KoinApiExtension
class MainViewModel(
    private val cRepository: CurrencyRepository

) : ViewModel(), KoinComponent {

    val currencyLiveDataBd: LiveData<List<CurrencyResult>> = cRepository.getCurrencyBd().asLiveData()

//    val nameListLiveDataYeasDay: MutableLiveData<MutableList<CurrencyResult>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            nameListLiveDataYeasDay.postValue(cRepository.getCurrenciesResult())
            cRepository.addCurrency()
        }
    }
}


