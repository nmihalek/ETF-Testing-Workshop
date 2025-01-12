package com.etf.testing.workshop.presentation.common

import android.app.Application
import com.etf.testing.workshop.di.repositoryModule
import com.etf.testing.workshop.di.retrofitModule
import com.etf.testing.workshop.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    repositoryModule,
                    retrofitModule,
                    viewModelModule
                )
            )
        }
    }
}