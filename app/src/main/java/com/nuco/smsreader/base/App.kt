package com.nuco.smsreader.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.nuco.smsreader.di.remoteDataModule
import com.nuco.smsreader.di.viewModels

/**
 * Application class
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModels, remoteDataModule))
        }
    }
}