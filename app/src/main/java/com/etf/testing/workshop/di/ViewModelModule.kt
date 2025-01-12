package com.etf.testing.workshop.di

import com.etf.testing.workshop.presentation.dogs.DogDetailsViewModel
import com.etf.testing.workshop.presentation.dogs.DogsListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::DogsListViewModel)
    viewModelOf(::DogDetailsViewModel)
}