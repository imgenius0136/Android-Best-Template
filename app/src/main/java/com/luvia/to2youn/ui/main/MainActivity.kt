package com.luvia.to2youn.ui.main

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.luvia.to2youn.base.BaseMvvmActivity
import com.luvia.to2youn.base.BaseViewModel
import com.luvia.to2youn.databinding.ActivityMainBinding

class MainActivity : BaseMvvmActivity<ActivityMainBinding>() {

    private val viewModel: SharedViewModel by viewModels()

    private val adapter : ViewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun init() {
        viewModel.init()
        initObserver()
        initListener()
        initViewPager()
    }

    private fun initObserver(){

    }

    private fun initListener(){

    }

    private fun initViewPager(){
        binding.viewPager.adapter = adapter
        adapter.notifyDataSetChanged()

        val tabTitles = adapter.getTitle()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position -> tab.text = tabTitles[position] }.attach()
    }
}