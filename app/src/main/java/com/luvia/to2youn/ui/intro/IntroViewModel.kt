package com.luvia.to2youn.ui.intro

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.luvia.to2youn.base.BaseViewModel

class IntroViewModel(application: Application) : BaseViewModel(application) {
    private val countDownFlag = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val startActivityFlag = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun getCountDownFlag(): MutableLiveData<Boolean> {
        return countDownFlag
    }

    fun getStartActivityFlag(): MutableLiveData<Boolean> {
        return startActivityFlag
    }
}