package com.example.testcurrency.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testcurrency.entities.CurrencyResponce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/api/exrates/rates?periodicity=0")
    suspend fun getCharacterList(): List<CurrencyResponce>

    @RequiresApi(Build.VERSION_CODES.O)
    @GET("https://www.nbrb.by/api/exrates/rates?&periodicity=0")
    suspend fun getCharacterListDay(
        @Query("ondate") ondate: String
    ): List<CurrencyResponce>

    companion object {
        private const val BASE_URL = "https://www.nbrb.by/"

        fun get(): CurrencyApi = Retrofit.Builder().baseUrl(BASE_URL)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build().create(CurrencyApi::class.java)
    }
}


