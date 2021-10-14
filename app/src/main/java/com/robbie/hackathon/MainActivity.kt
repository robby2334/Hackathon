package com.robbie.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.robbie.hackathon.adapter.ViewPagerAdapter
import com.robbie.hackathon.databinding.ActivityMainBinding
import com.robbie.hackathon.ui.FragmentDetailProfile
import com.robbie.hackathon.ui.PeopleLocation

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupAdapter()

    }
    private fun setupAdapter(){
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(FragmentDetailProfile(),getString(R.string.tab_layout_text_profile))
        viewPagerAdapter.addFragment(PeopleLocation(),getString(R.string.tab_layout_text_current_location))
        binding.vpMenu.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tlMenu, binding.vpMenu, true){
            tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
        }.attach()

    }

}