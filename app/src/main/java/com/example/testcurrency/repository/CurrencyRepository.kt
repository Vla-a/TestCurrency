package com.example.testcurrency.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testcurrency.data.CurrencyResult
import com.example.testcurrency.database.CurrencyDao
import com.example.testcurrency.database.CurrencyEntity
import com.example.testcurrency.restApi.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CurrencyRepository(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi
) {

    @RequiresApi(Build.VERSION_CODES.O)


    suspend fun addCurrency() {
        currencyDao.addCyrrencyBd(
            withContext(Dispatchers.IO) {
                currencyApi.getCharacterList().map {
                    CurrencyEntity(
                        it.id,
                        it.numCod,
                        it.charCode,
                        it.scale,
                        it.name,
                        it.rate
                    )
                }
            })
    }

    fun getCurrencyBd(): Flow<List<CurrencyResult>> =
        currencyDao.getCurrencyListBd().map { currencyEntity ->
            currencyEntity.map {
                CurrencyResult(
                    it.id,
                    it.numCod,
                    it.charCode,
                    it.scale,
                    it.name,
                    it.rate
                )
            }
        }
}
