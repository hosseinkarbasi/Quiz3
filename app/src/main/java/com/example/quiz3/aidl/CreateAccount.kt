package com.example.quiz3.aidl

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quiz3.R
import com.example.quiz3.StoreId
import com.example.quiz3.UserInfo
import com.example.quiz3.databinding.CreateAccountBinding
import com.example.quiz3.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccount : Fragment(R.layout.create_account) {
    private lateinit var binding: CreateAccountBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {

        val hobbiesList = arrayListOf<String>()
        hobbiesList.add("Movie")
        hobbiesList.add("Sport")
        NetworkManager.service.createAccount(
            UserInfo(
                binding.edFirstname.text.toString(),
                binding.edLastname.text.toString(),
                binding.edNationalcode.text.toString(),
                hobbiesList
            )
        ).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Tag", response.body().toString())
                Log.d("success", "success")

                val loginResponse = response.body()
                var storeId = StoreId(requireContext())
                storeId.saveId(loginResponse.toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }
        })
    }
}