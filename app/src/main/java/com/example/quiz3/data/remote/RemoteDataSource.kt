package com.example.quiz3.data.remote

import com.example.quiz3.data.DataSource
import com.example.quiz3.data.Mapper
import com.example.quiz3.data.model.User
import com.example.quiz3.data.remote.model.UsersListItem
import com.example.quiz3.data.remote.network.UserApi

class RemoteDataSource(private val userApi: UserApi) : DataSource {
    override fun insertUsers(users: List<User>) {
    }

    override fun getUsers(): List<User> {
        val result: List<UsersListItem> = userApi.getUsers().execute().body() ?: emptyList()
        return result.map {
         Mapper.transformToUser(it)
        }
    }
}