package com.etf.testing.workshop.di

import com.etf.testing.workshop.data.dogs.DogCeoDogsRepository
import com.etf.testing.workshop.domain.DogsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DogsRepository> { DogCeoDogsRepository(get()) }
}