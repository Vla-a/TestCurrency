package com.example.testcurrency

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.screen.MainViewModel
import com.example.currency.restApi.CurrencyRepository
import com.example.myhomework.homework13.sharedprefs.SharedPrefsKeyss
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtilss
import com.example.testcurrency.database.CurrencyDatabase
import com.example.testcurrency.database.DatabaseConstructor
import com.example.testcurrency.restApi.CurrencyApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MySuperApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        SharedPrefsUtilss.sharedPrefs =
            applicationContext.getSharedPreferences(SharedPrefsKeyss.LOGIN, MODE_PRIVATE)

        SharedPrefsUtilss.sharedPrefs =
            applicationContext.getSharedPreferences(SharedPrefsKeyss.PASSWORD, MODE_PRIVATE)

        startKoin {
            androidContext(this@MySuperApp)
            modules(listOf(repositoryModule, viewModels, currencyApi, storageModule))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val viewModels = module {
        viewModel { MainViewModel(get()) }

    }

    private val repositoryModule = module { //создаем репозитории

        factory { CurrencyRepository(get(), get()) }

    }

    private val currencyApi = module {
        single { CurrencyApi.get() }
    }
    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }  //создаем синглтон базы данных
        factory { get<CurrencyDatabase>().CurrencyDao() } //предоставляем доступ для конкретной Dao (в нашем случае NotesDao)

    }

}