package com.etf.testing.workshop.di

import com.etf.testing.workshop.data.dogs.DogCeoService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val retrofitModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(DogCeoService::class.java)
    }
}
