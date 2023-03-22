package com.example.traineemoveapp.di.module

import android.content.Context
import com.example.traineemoveapp.data.room.TraineeMoveDatabase
import com.example.traineemoveapp.data.repository.RepositoryDB
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideRepositoryDB(traineeMoveDatabase: TraineeMoveDatabase): RepositoryDB {
        return RepositoryDB(traineeMoveDatabase)
    }
    @Provides
    fun provideTraineeMoveDatabase(context: Context): TraineeMoveDatabase {
        return TraineeMoveDatabase.createInstance(context)
    }
}
