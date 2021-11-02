package com.example.testcurrency.databaseUser

import androidx.room.Entity

 @Entity(tableName = "user_table", primaryKeys = ["login", "password"])
data class UserEntity(
    val login: String,
    val password: String
)