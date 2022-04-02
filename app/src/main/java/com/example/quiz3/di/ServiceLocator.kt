package com.example.quiz3.di

import android.app.Application
import com.example.quiz3.data.UserRepository
import com.example.quiz3.data.local.LocalDataSource
import com.example.quiz3.data.local.db.AppDataBase
import com.example.quiz3.data.remote.RemoteDataSource
import com.example.quiz3.data.remote.network.NetworkManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServiceLocator(application: Application) {

    private val remoteDataSource = RemoteDataSource(NetworkManager.service)
    private val localDataSource = LocalDataSource(AppDataBase.getDatabase(application).userDao())
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    val userRepository = UserRepository(executorService, remoteDataSource, localDataSource)

}