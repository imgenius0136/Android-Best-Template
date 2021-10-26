package com.luvia.to2youn.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter constructor(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    companion object {
        const val Fragment1 = 0
        const val Fragment2 = 1
    }

    private val fragmentCount = 2

    private val tabTitles = listOf<String>("Fragment1", "Fragment2")

    fun getTitle(): List<String> {
        return tabTitles
    }

    override fun getItemCount(): Int = fragmentCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
//            FRAGMENT_SEARCH -> Fragment.newInstance()
//            FRAGMENT_BOOKMARK -> Fragment.newInstance()
            else -> null!!
        }
    }
}