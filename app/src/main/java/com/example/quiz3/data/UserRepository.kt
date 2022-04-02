package com.example.quiz3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz3.data.model.User
import java.util.concurrent.ExecutorService

class UserRepository(
    private val executorService: ExecutorService,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource
) {

    fun getUsers(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()

        executorService.submit {
            val remoteData = remoteDataSource.getUsers()
            localDataSource.insertUsers(remoteData)
            liveData.postValue(remoteData)
        }
        return liveData
    }
}