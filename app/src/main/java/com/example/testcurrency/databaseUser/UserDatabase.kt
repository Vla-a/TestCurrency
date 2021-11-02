package com.example.testcurrency.databaseUser

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testcurrency.database.CurrencyDao
import com.example.testcurrency.database.CurrencyEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao
}

object DatabaseConstructorUser {
    fun create(context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).build()

}