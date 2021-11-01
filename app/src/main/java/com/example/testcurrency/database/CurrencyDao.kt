package com.example.testcurrency.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM character_table")
    fun getCurrencyListBd(): Flow<MutableList<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCyrrencyBd(currency:List<CurrencyEntity>)

}