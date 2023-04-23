package com.example.traineemoveapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traineemoveapp.data.repository.Repository
import com.example.traineemoveapp.viewModel.MainActivityViewModel

class MainActivityViewModelFactory (
        val repository: Repository
        ):ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainActivityViewModel( repository = repository) as T
            }
        }