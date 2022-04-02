package com.example.quiz3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quiz3.data.UserRepository
import com.example.quiz3.ui.users.UsersListViewModel

class CustomViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            UsersListViewModel(userRepository) as T
        } else {
            modelClass.newInstance()
        }
    }
}