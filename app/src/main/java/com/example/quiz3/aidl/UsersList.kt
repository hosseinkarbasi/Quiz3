package com.example.quiz3.aidl

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3.R
import com.example.quiz3.RecyclerAdapter
import com.example.quiz3.databinding.UsersListBinding
import com.example.quiz3.model.UsersList
import com.example.quiz3.model.UsersListItem
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersList : Fragment(R.layout.users_list) {
    lateinit var recyclerView: RecyclerView
    var dataList = UsersList()
    private var myAdapter: RecyclerAdapter? = null
    private lateinit var binding: UsersListBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView_main)
        binding = DataBindingUtil.bind(view)!!
        fetchJson()
        myAdapter = RecyclerAdapter(dataList)
        binding.recyclerViewMain.adapter = myAdapter
        binding.recyclerViewMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.tv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                getFilter(p0.toString())
            }

        })
    }

    private fun getFilter(text: String) {
        val filterNames = UsersList()
        dataList.filterTo(filterNames) {
            it.firstName.lowercase().contains(text.lowercase()) || it.nationalCode.lowercase()
                .contains(text.lowercase())
        }
        if (filterNames != null) {
            myAdapter!!.filterList(filterNames)
        }
    }

    private fun fetchJson() {
        NetworkManager.service.getUsers().enqueue(object : Callback<UsersList> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                Log.d("Tag", response.body().toString())
                Log.d("Tag", "success")
                dataList.addAll(response.body()!!)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                Log.d("Tag", "Failure")
            }
        })
    }
}