package com.example.quiz3.model

data class UsersListItem(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: MutableList<String>,
    val image: String
)