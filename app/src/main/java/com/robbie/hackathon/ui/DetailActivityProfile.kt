package com.robbie.hackathon.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.robbie.hackathon.data.DataProfile
import com.robbie.hackathon.databinding.ActivityDetailProfileBinding

class DetailActivityProfile : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }


    private fun getData() {
        val dataProfile = intent.getParcelableExtra<DataProfile>(KEY_DATA)

        binding.tvNameProfile.text = dataProfile?.name
        binding.tvProfessionProfile.text = dataProfile?.Profession
        binding.tvAddressProfile.text = dataProfile?.address

        Glide.with(this)
            .load(IMAGE_PROFILE_URL)
            .circleCrop()
            .into(binding.ivImageProfile)
    }

    companion object {
        const val KEY_DATA = "KEY_DATA"
        const val IMAGE_PROFILE_URL = "https://lh3.googleusercontent.com/ogw/ADea4I6z8YkONrOxaXrFo0HWef5b33miL58JkwEjQG7l=s83-c-mo"



        @JvmStatic
        fun startActivity(context: Context?, data: DataProfile) {
            val intent = Intent(context, DetailActivityProfile::class.java)
            intent.putExtra(KEY_DATA, data)
            context?.startActivity(intent)
        }
    }


}