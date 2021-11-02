package com.example.testcurrency.databaseUser

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUserBd(): Flow<MutableList<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserBd(user:UserEntity)

    @Delete
    suspend fun deleteUserBd(user:UserEntity)

}