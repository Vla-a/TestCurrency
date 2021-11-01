package com.example.testcurrency.database

import androidx.room.Entity

@Entity(tableName = "character_table", primaryKeys = ["id", "name"])
class CurrencyEntity(
    val id: Long,
    val numCod: String,
    val charCode: String,
    val scale: Int,
    val name: String,
    val rate: Double
)