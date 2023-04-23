package com.example.traineemoveapp

import android.app.Application
import com.example.traineemoveapp.di.AppComponent
import com.example.traineemoveapp.di.DaggerAppComponent

open class MyApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}




