package com.example.traineemoveapp.data.repository

import javax.inject.Inject

class Repository @Inject constructor (val remoteRepository: RemoteRepository, val repositoryDB: RepositoryDB)