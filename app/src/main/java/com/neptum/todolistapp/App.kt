package com.neptum.todolistapp

import android.app.Application
import com.google.firebase.FirebaseApp
import com.neptum.todolistapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}