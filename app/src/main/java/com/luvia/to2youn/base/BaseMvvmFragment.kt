package com.luvia.to2youn.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseMvvmFragment<T> : Fragment() where T : ViewBinding {

    lateinit var binding : T

    abstract fun getViewBinding() : T
    abstract fun getViewModel() : ViewModel
    abstract fun init()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getViewBinding()

        init()

        return binding.root
    }

}