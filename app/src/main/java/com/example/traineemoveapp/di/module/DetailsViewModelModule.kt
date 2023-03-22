package com.example.traineemoveapp.di.module

import com.example.traineemoveapp.di.factory.DetailsViewModelFactory
import com.example.traineemoveapp.repository.RemoteRepository
import dagger.Module
import dagger.Provides

@Module
class DetailsViewModelModule {
    @Provides
    fun provideDetailFilmViewModel(filmRepository: RemoteRepository): DetailsViewModelFactory {
        return DetailsViewModelFactory(filmRepository = filmRepository)
    }
}