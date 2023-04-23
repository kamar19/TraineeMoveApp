package com.example.traineemoveapp.di.module

import com.example.traineemoveapp.di.factory.DetailsViewModelFactory
import com.example.traineemoveapp.data.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class DetailsViewModelModule {
    @Provides
    fun provideDetailFilmViewModel(repository: Repository): DetailsViewModelFactory {
        return DetailsViewModelFactory(repository = repository)
    }
}