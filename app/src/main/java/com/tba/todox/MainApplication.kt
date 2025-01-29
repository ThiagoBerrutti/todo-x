package com.tba.todox

import android.app.Application
import com.tba.todox.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidLogger()
            androidContext(this@MainApplication)

        }
    }
}