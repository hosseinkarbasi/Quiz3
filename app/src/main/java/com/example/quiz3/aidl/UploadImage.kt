package com.example.quiz3.aidl

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quiz3.R
import com.example.quiz3.StoreId
import com.example.quiz3.databinding.UploadImageBinding
import com.example.quiz3.network.NetworkManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class UploadImage : Fragment(R.layout.upload_image) {
    private lateinit var imageAddresses: Uri
    private lateinit var binding: UploadImageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.selectImageFromGallery()
        binding.btnUpload.setOnClickListener {
            val stream: InputStream? =
                requireContext().contentResolver.openInputStream(imageAddresses)
            uploadImage(stream!!.readBytes())
        }
    }

    private fun UploadImageBinding.selectImageFromGallery() {
        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            imageAddresses = it
            btnSelectImgProfile.setImageURI(it)
        }
        btnSelectImgProfile.setOnClickListener {
            loadImage.launch("image/*")
        }
    }

    private fun uploadImage(src: ByteArray) {
        val storeId = StoreId(requireContext())
        val part = MultipartBody.Part.createFormData(
            "image",
            "hosseink.png",
            RequestBody.create(MediaType.parse("image/*"), src)
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