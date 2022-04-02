package com.example.quiz3.data.local

import com.example.quiz3.data.DataSource
import com.example.quiz3.data.local.db.UserDao
import com.example.quiz3.data.model.User

class LocalDataSource(private val userDao: UserDao) : DataSource {
    override fun insertUsers(users: List<User>) {
       return userDao.insertUsers(*users.toTypedArray())
    }

    override fun getUsers(): List<User> {
        return userDao.getUsers()
    }
}