package com.luvia.to2youn.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.luvia.to2youn.common.PreferenceManager

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    val preferenceManager: PreferenceManager by lazy {
        PreferenceManager(getApplication<Application>().applicationContext)
    }

    val progress = MutableLiveData<Boolean>().apply {
        value = false
    }
}