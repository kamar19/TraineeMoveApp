package com.example.traineemoveapp.di.module

import com.example.traineemoveapp.di.factory.MainActivityViewModelFactory
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainActivityViewModelFactory(repositoryRemote: RemoteRepository, repositoryDB: RepositoryDB): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(repositoryRemote = repositoryRemote, repositoryDB = repositoryDB)
    }
}