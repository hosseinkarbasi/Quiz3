package com.example.quiz3.ui.users

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz3.model.UsersList
import com.example.quiz3.model.UsersListItem
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListViewModel : ViewModel() {

    var listUsers = MutableLiveData<UsersList>()
    var searchUsers = MutableLiveData<UsersList>()

    fun getUsersList() {
        NetworkManager.service.getUsers().enqueue(object : Callback<UsersList> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                Log.d("Tag", response.body().toString())
                Log.d("Tag", "success")
                listUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                Log.d("Tag", "Failure")
            }
        })
    }

    fun searchFromUsers(query: HashMap<String, String>) {
        NetworkManager.service.getUsers(query).enqueue(object : Callback<UsersList> {
            override fun onResponse(
                call: Call<UsersList>, response: Response<UsersList>) {
                searchUsers.postValue(response.body())
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                Log.d("TAG", "searchResult:" + t.message.toString())
            }
        })
    }

    fun getUserFromFirstName(firstname:String){
        if (firstname.isNotBlank()){
            searchFromUsers( hashMapOf("firstName" to firstname))
        }
    }
}