package com.example.quiz3.network

import com.example.quiz3.UserInfo
import com.example.quiz3.model.UsersList
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface GitHubService {

    @POST("users")
    fun createAccount(
        @Body userData: UserInfo
    ): Call<String>


    @Multipart
    @POST("users/{id}/image")
    fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Call<String>

    @GET("users")
    fun getUsers() :Call<UsersList>
}