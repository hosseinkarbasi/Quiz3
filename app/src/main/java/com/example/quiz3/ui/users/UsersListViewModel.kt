package com.example.quiz3.ui.users

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz3.data.UserRepository
import com.example.quiz3.data.model.User
import com.example.quiz3.data.remote.model.UsersListItem
import com.example.quiz3.data.remote.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListViewModel(private val userRepository: UserRepository) : ViewModel() {

    var listUsers = MutableLiveData<List<UsersListItem>>()
    var searchUsers = MutableLiveData<List<UsersListItem>>()


    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUsers()
    }

    fun getUsersList() {
        NetworkManager.service.getUsers().enqueue(object : Callback<List<UsersListItem>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UsersListItem>>,
                response: Response<List<UsersListItem>>
            ) {
                Log.d("Tag", response.body().toString())
                Log.d("Tag", "success")
                listUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UsersListItem>>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                Log.d("Tag", "Failure")
            }
        })
    }

    private fun searchFromUsers(query: HashMap<String, String>) {
        NetworkManager.service.getUsers(query).enqueue(object : Callback<List<UsersListItem>> {
            override fun onResponse(
                call: Call<List<UsersListItem>>, response: Response<List<UsersListItem>>
            ) {
                searchUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UsersListItem>>, t: Throwable) {
                Log.d("TAG", "searchResult:" + t.message.toString())
            }
        })
    }

    fun getUserFromFirstName(firstname: String) {
        if (firstname.isNotBlank()) {
            searchFromUsers(hashMapOf("firstName" to firstname))
        }
    }
}