package com.bankaccenture.retrofit

import com.bankaccenture.retrofit.service.NetworkApiSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://bank-app-test.herokuapp.com/api/"

class AppRetrofit {

    private val cliente by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptador).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()
    }

    val networkApiSource: NetworkApiSource by lazy {
        retrofit.create(NetworkApiSource::class.java)
    }
}