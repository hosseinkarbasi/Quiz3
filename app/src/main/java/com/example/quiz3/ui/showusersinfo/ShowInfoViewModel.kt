package com.example.quiz3.ui.showusersinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz3.model.UserInfo
import com.example.quiz3.model.UsersListItem
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowInfoViewModel : ViewModel() {

    private val _user = MutableLiveData<UsersListItem>()
    val user: LiveData<UsersListItem> = _user

    fun showInfo(id: String) {
        NetworkManager.service.getShowInfo(id).enqueue(object : Callback<UsersListItem> {
            override fun onResponse(
                call: Call<UsersListItem>, response: Response<UsersListItem>
            ) {
                _user.postValue(response.body())
                Log.d("response", response.body()!!._id)
            }

            override fun onFailure(call: Call<UsersListItem>, t: Throwable) {
                Log.d("showDetails", t.message.toString())
            }
        })
    }
}