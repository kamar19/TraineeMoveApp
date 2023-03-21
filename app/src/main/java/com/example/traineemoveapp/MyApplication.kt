package com.example.traineemoveapp

import android.app.Application
import android.content.Context
import com.example.traineemoveapp.data.remote.RemoteDataSource
import com.example.traineemoveapp.data.room.TraineeMoveDatabase
import com.example.traineemoveapp.di.AppComponent
import com.example.traineemoveapp.di.DaggerAppComponent
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import dagger.Component
import dagger.internal.DaggerGenerated

import dagger.internal.Preconditions


//@Component
//interface ApplicationComponent {
//    val repositoryDB: RepositoryDB
//    val filmRepository: RemoteRepository
//}

open class MyApplication: Application() {
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    open val remoteRepository by lazy {
        RemoteRepository(RemoteDataSource())
    }
    open val repositoryDB by lazy {
        RepositoryDB(TraineeMoveDatabase.createInstance(this))
    }

}




