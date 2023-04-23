package com.example.traineemoveapp.di.module

import com.example.traineemoveapp.data.repository.RemoteRepository
import com.example.traineemoveapp.data.repository.Repository
import com.example.traineemoveapp.data.repository.RepositoryDB
import dagger.Module
import dagger.Provides

@Module (includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {
    @Provides
    fun provideRepository(repositoryDB: RepositoryDB, remoteRepository: RemoteRepository): Repository
    {
        return Repository(repositoryDB = repositoryDB, remoteRepository = remoteRepository)
    }

}