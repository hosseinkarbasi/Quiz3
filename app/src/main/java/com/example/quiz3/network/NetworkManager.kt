package com.example.quiz3.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(getLogging())
            .addInterceptor { chain ->
                val request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer karbasiiiiiii")
                        .build()
                chain.proceed(request)
            }
            .build()
    }

    private fun getLogging(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://papp.ir/api/v1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: GitHubService = retrofit.create(GitHubService::class.java)

}