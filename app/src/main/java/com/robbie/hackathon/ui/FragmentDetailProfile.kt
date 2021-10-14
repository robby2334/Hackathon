package com.robbie.hackathon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.robbie.hackathon.R
import com.robbie.hackathon.data.DataProfile
import com.robbie.hackathon.databinding.FragmentDetailProfileBinding
import com.robbie.hackathon.ui.DetailActivityProfile.Companion.IMAGE_PROFILE_URL


class FragmentDetailProfile : Fragment() {
    private lateinit var binding: FragmentDetailProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireActivity())
            .load(IMAGE_PROFILE_URL)
            .circleCrop()
            .into(binding.ivImageProfile)
        binding.tvNameProfile.text = getString(R.string.text_name_profile)
        binding.tvProfessionProfile.text = getString(R.string.text_profession)
        binding.tvAddressProfile.text = getString(R.string.text_address)

        navigateToDetail()

    }

    private fun navigateToDetail() {
        binding.clCardProfile.setOnClickListener {
            DetailActivityProfile.startActivity(
                requireContext(), DataProfile(
                    getString(R.string.text_name_profile),
                    getString(R.string.text_profession),
                    getString(R.string.text_address),
                )
            )
        }
    }

}

