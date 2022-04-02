package com.example.quiz3.data.remote.network

import com.example.quiz3.data.remote.model.UserInfo
import com.example.quiz3.data.remote.model.UsersListItem
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

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
    fun getUsers(@QueryMap users: HashMap<String, String> = hashMapOf()): Call<List<UsersListItem>>

    @GET("http://papp.ir/api/v1/users/{id}")
    fun getShowInfo(@Path("id") id: String): Call<UsersListItem>
}