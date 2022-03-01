package com.example.quiz3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3.model.UsersList

class RecyclerAdapter(private val homeFeed: UsersList) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.show_user, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val firstName = homeFeed[position]
        holder.tvfirstName.text = firstName.firstName
    }
}

class CustomViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    var tvfirstName: TextView = view.findViewById(R.id.edUser)
}