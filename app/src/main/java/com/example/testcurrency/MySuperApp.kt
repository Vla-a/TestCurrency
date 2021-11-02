package com.example.testcurrency

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testcurrency.viewModel.MainViewModel
import com.example.testcurrency.database.CurrencyDatabase
import com.example.testcurrency.database.DatabaseConstructor
import com.example.testcurrency.databaseUser.DatabaseConstructorUser
import com.example.testcurrency.databaseUser.UserDatabase
import com.example.testcurrency.repository.CurrencyRepository
import com.example.testcurrency.repository.UserRepository
import com.example.testcurrency.restApi.CurrencyApi
import com.example.testcurrency.viewModel.SharedViewModel
import com.example.testcurrency.viewModel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MySuperApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MySuperApp)
            modules(listOf(repositoryModule, viewModels, currencyApi, storageModule))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val viewModels = module {
        viewModel { MainViewModel(get()) }
        viewModel { UserViewModel(get()) }
        viewModel { SharedViewModel() }
    }

    private val repositoryModule = module { //создаем репозитории

        factory { CurrencyRepository(get(), get()) }
        factory { UserRepository(get()) }
    }

    private val currencyApi = module {
        single { CurrencyApi.get() }
    }
    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }  //создаем синглтон базы данных
        factory { get<CurrencyDatabase>().CurrencyDao() } //предоставляем доступ для конкретной Dao (в нашем случае NotesDao)
        single { DatabaseConstructorUser.create(get()) }
        factory { get<UserDatabase>().UserDao() }

    }

}