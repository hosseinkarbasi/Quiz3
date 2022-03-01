package com.example.quiz3.aidl

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3.R
import com.example.quiz3.RecyclerAdapter
import com.example.quiz3.model.UsersList
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListFragment : Fragment(R.layout.users_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchJson()

    }

    private fun fetchJson() {
        NetworkManager.service.getUsers().enqueue(object : Callback<UsersList> {
            override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                Log.d("Tag", response.body().toString())
                Log.d("Tag", "success")
                val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_main)
                recyclerView?.adapter = RecyclerAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                Log.d("Tag", "Failure")
            }
        })
    }
}