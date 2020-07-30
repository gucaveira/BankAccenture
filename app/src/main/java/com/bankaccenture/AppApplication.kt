package com.bankaccenture

import android.app.Application
import com.bankaccenture.di.uiModule
import com.bankaccenture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    uiModule,
                    viewModelModule
                )
            )
        }
    }
}