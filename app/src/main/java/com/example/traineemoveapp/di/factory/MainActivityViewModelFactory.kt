package com.example.traineemoveapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import com.example.traineemoveapp.viewModel.MainActivityViewModel

class MainActivityViewModelFactory (
        val repositoryRemote: RemoteRepository, val repositoryDB: RepositoryDB
        ):ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainActivityViewModel( repositoryRemote = repositoryRemote, repositoryDB = repositoryDB) as T
            }
        }