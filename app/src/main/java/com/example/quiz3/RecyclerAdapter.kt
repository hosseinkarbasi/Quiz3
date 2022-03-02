package com.example.quiz3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz3.model.UsersList

class RecyclerAdapter(private var homeFeed: UsersList) :
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
        val usersInfo = homeFeed[position]
        holder.tvFirstName.text = usersInfo.firstName
        holder.tvLastName.text = usersInfo.lastName
        holder.tvNationalCode.text = usersInfo.nationalCode
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredNames: UsersList) {
        this.homeFeed = filteredNames
        notifyDataSetChanged()
    }

}

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvFirstName: TextView = view.findViewById(R.id.tvFirstName)
    var tvLastName: TextView = view.findViewById(R.id.tvLastName)
    var tvNationalCode: TextView = view.findViewById(R.id.tvNationalCode)
}