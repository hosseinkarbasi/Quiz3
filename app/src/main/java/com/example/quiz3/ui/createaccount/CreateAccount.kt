package com.example.quiz3.ui.createaccount

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quiz3.R
import com.example.quiz3.model.UserInfo
import com.example.quiz3.databinding.CreateAccountBinding

class CreateAccount : Fragment(R.layout.create_account) {
    private lateinit var binding: CreateAccountBinding
    private val viewModelCreateAccount: CreateAccountViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val hobbiesList = arrayListOf<String>()
        if (binding.coding.isChecked) {
            hobbiesList.add(binding.coding.text.toString())
        }
        if (binding.movie.isChecked) {
            hobbiesList.add(binding.movie.text.toString())
        }

        val user = UserInfo(
            binding.edFirstname.text.toString(),
            binding.edLastname.text.toString(),
            binding.edNationalcode.text.toString(),
            hobbiesList
        )

        viewModelCreateAccount.createAccount(user, requireContext())
        findNavController().navigate(R.id.action_createAccount_to_uploadImage)

    }
}