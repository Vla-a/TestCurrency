package com.example.testcurrency.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testcurrency.databaseUser.UserEntity
import com.example.testcurrency.repository.UserRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class UserViewModel(
    private val userRepository: UserRepository

) : ViewModel(), KoinComponent {

    val userLiveDataBd: LiveData<List<UserEntity>> = userRepository.getUserBd().asLiveData()

    fun addUserDb(login: String, password: String) {

        val newUser = UserEntity(login, password)

        viewModelScope.launch {
            userRepository.addUserBd(newUser)
        }
    }

    fun deleteUserBd(user: UserEntity) {

        viewModelScope.launch {
            userRepository.deleteUserBd(user)
        }
    }
}