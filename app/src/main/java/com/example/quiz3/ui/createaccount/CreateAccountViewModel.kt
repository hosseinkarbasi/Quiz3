package com.example.quiz3.ui.createaccount

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quiz3.ui.StoreId
import com.example.quiz3.model.UserInfo
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CreateAccountViewModel : ViewModel() {

   fun createAccount(user: UserInfo,context: Context) {
        NetworkManager.service.createAccount(user).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Tag", response.body().toString())
                Log.d("success", "success")

                val loginResponse = response.body()
                val storeId = StoreId(context)
                storeId.saveId(loginResponse.toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }
        })
    }
}