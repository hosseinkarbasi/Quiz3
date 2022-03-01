package com.example.quiz3

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("nationalCode") val nationalCode: String?,
    @SerializedName("hobbies") val hobbies: ArrayList<String>
)