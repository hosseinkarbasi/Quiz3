package com.example.quiz3.ui.uploadimage

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quiz3.R
import com.example.quiz3.databinding.UploadImageBinding
import java.io.InputStream

class UploadImage : Fragment(R.layout.upload_image) {
    private lateinit var imageAddresses: Uri
    private lateinit var binding: UploadImageBinding
    private val viewModelUploadImage: UploadImageViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        binding.selectImageFromGallery()
        uploadImage()

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

    private fun uploadImage() {
        binding.btnUpload.setOnClickListener {
            val stream: InputStream? =
                requireContext().contentResolver.openInputStream(imageAddresses)

            viewModelUploadImage.uploadImage(stream!!.readBytes(), requireContext())
        }
    }
}