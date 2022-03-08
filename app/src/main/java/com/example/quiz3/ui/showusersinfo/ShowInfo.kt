package com.example.quiz3.ui.showusersinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.quiz3.R
import com.example.quiz3.databinding.ShowInfoBinding

class ShowInfo : Fragment(R.layout.show_info) {

    lateinit var binding: ShowInfoBinding
    private val args by navArgs<ShowInfoArgs>()
    private val viewModel: ShowInfoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ShowInfoBinding.bind(view)

        viewModel.showInfo(args.id)

        viewModel.user.observe(viewLifecycleOwner) {
            binding.tvFirstName.text = it.firstName
            binding.tvLastName.text = it.lastName
            binding.tvNationalCode.text = it.nationalCode
            binding.tvHobbie.text = it.hobbies.toString()

            Glide
                .with(requireContext())
                .load(it.image)
                .into(binding.imgProfile)
        }
    }
}