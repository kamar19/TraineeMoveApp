package com.example.traineemoveapp.di.module

import com.example.traineemoveapp.di.factory.MainActivityViewModelFactory
import com.example.traineemoveapp.data.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainActivityViewModelFactory(repository: Repository): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(repository = repository)
    }
}