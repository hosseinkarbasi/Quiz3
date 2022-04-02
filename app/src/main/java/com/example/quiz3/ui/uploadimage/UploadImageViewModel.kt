package com.example.quiz3.ui.uploadimage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quiz3.ui.StoreId
import com.example.quiz3.data.remote.network.NetworkManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadImageViewModel : ViewModel() {

     fun uploadImage(src: ByteArray,context: Context) {
        val storeId = StoreId(context)
        val part = MultipartBody.Part.createFormData(
            "image",
            "hosseink.png",
            RequestBody.create("image/*".toMediaTypeOrNull(), src)
        )

        storeId.fetchId()?.let {
            NetworkManager.service.uploadImage(it, part).enqueue(
                object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("Tag", "success")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("Tag", t.message.toString())
                    }
                })
        }
    }
}