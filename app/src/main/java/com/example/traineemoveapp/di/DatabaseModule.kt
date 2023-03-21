package com.example.traineemoveapp.di

import android.content.Context
import com.example.traineemoveapp.data.remote.RemoteDataSource
import com.example.traineemoveapp.data.room.TraineeMoveDatabase
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideRepositoryDB(traineeMoveDatabase: TraineeMoveDatabase): RepositoryDB
    {
        return RepositoryDB(traineeMoveDatabase)
    }

    @Provides
    fun provideTraineeMoveDatabase(context: Context): TraineeMoveDatabase
    {
        return TraineeMoveDatabase.createInstance(context)
    }

}
