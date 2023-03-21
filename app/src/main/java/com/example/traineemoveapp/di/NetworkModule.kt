package com.example.traineemoveapp.di

import com.example.traineemoveapp.data.remote.RemoteDataSource
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideRemoteRepository(remoteDataSource: RemoteDataSource): RemoteRepository
    {
       return RemoteRepository(remoteDataSource)
    }

    @Provides
    fun provideRemoteDataSource(): RemoteDataSource
    {
        return RemoteDataSource()
    }

}
