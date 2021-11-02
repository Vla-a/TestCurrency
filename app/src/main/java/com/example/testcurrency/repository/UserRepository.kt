package com.example.testcurrency.repository

import com.example.testcurrency.data.User
import com.example.testcurrency.databaseUser.UserDao
import com.example.testcurrency.databaseUser.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val userDao: UserDao
) {
    fun getUserBd(): Flow<List<UserEntity>> =
        userDao.getUserBd().map { user ->
            user.map {
                UserEntity(
                    it.login,
                    it.password
                )
            }
        }

    suspend fun addUserBd(user: UserEntity) {
        userDao.addUserBd(user)
    }

    suspend fun deleteUserBd(user: UserEntity) {
        userDao.deleteUserBd(user)
    }

    private fun User.entity() = UserEntity(this.login, this.password)
}