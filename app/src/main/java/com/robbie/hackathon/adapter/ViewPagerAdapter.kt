package com.robbie.hackathon.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle){

    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val fragmentTitleList: MutableList<String> = mutableListOf()

    fun addFragment(fragment: Fragment, title: String) {
        if (fragment.isAdded && fragmentList.contains(fragment)) {
            return
        }
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getPageTitle(position : Int) : String {
        return fragmentTitleList[position]
    }
}