package com.example.quiz3.data

import com.example.quiz3.data.model.User

interface DataSource {
    fun getUsers(): List<User>
    fun insertUsers(users: List<User>)
}