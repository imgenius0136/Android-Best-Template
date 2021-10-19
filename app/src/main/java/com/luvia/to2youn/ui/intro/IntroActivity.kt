package com.luvia.to2youn.ui.intro

import androidx.activity.viewModels
import com.luvia.to2youn.base.BaseMvvmActivity
import com.luvia.to2youn.base.BaseViewModel
import com.luvia.to2youn.databinding.ActivityIntroBinding

class IntroActivity : BaseMvvmActivity<ActivityIntroBinding>() {

    private val viewModel: IntroViewModel by viewModels()

    override fun init() {

    }

    override fun getViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

}