package com.example.traineemoveapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.viewModel.DetailFilmViewModel

class DetailsViewModelFactory (
        val filmRepository: RemoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailFilmViewModel(filmRepository = filmRepository) as T
    }
}