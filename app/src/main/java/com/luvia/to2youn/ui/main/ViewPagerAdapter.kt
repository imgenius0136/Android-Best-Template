package com.luvia.to2youn.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.luvia.to2youn.ui.main.bookmark.BookmarkFragment
import com.luvia.to2youn.ui.main.search.SearchFragment

class ViewPagerAdapter constructor(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    companion object {
        const val FRAGMENT_SEARCH = 0
        const val FRAGMENT_BOOKMARK = 1
    }

    private val fragmentCount = 2

    private val tabTitles = listOf<String>("SEARCH", "BOOKMARK")

    fun getTitle(): List<String> {
        return tabTitles
    }

    override fun getItemCount(): Int = fragmentCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FRAGMENT_SEARCH -> SearchFragment.newInstance()
            FRAGMENT_BOOKMARK -> BookmarkFragment.newInstance()
            else -> null!!
        }
    }
}