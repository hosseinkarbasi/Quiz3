package com.example.quiz3.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3.R
import com.example.quiz3.databinding.UsersListBinding
import com.example.quiz3.model.UsersList

class UsersList : Fragment(R.layout.users_list) {

    private lateinit var recyclerView: RecyclerView
    private var listUsers = UsersList()
    private lateinit var myAdapter: RecyclerAdapter
    private lateinit var binding: UsersListBinding
    private val viewModel: UsersListViewModel by activityViewModels()
    val navController by lazy { findNavController() }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        recyclerView = view.findViewById(R.id.recyclerView_main)
        myAdapter = RecyclerAdapter(listUsers)
        binding.recyclerViewMain.adapter = myAdapter
        binding.recyclerViewMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.getUsersList()

        viewModel.listUsers.observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            myAdapter.notifyDataSetChanged()
        }

        viewModel.searchUsers.observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            myAdapter.notifyDataSetChanged()
        }

        binding.btnSearch.setOnClickListener {
            viewModel.getUserFromFirstName(binding.searchTv.text.toString())
        }

        myAdapter.setItemUserClick(object : RecyclerAdapter.ItemClick {
            override fun viewClick(position: Int, v: View?) {
                navController.navigate(UsersListDirections.actionUsersListToShowInfo(listUsers[position]._id))
            }
        })

    }
}