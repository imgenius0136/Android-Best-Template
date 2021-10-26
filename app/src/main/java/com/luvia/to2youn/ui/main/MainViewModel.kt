package com.luvia.to2youn.ui.main

import android.app.Application
import com.luvia.to2youn.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    private val mainRepository = MainRepositoryImpl()

}