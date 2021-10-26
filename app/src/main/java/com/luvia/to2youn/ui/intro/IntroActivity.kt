package com.luvia.to2youn.ui.intro

import android.animation.Animator
import android.content.Intent
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.luvia.to2youn.base.BaseMvvmActivity
import com.luvia.to2youn.base.BaseViewModel
import com.luvia.to2youn.databinding.ActivityIntroBinding
import com.luvia.to2youn.ui.main.MainActivity

class IntroActivity : BaseMvvmActivity<ActivityIntroBinding>() {

    private val viewModel: IntroViewModel by viewModels()

    private val countDownTimer = object : CountDownTimer(4000, 1000) {

        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            viewModel.getStartActivityFlag().value = true
        }
    }

    override fun getViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun init() {
        initObserver()
        binding.lottieIntroView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                viewModel.getCountDownFlag().value = true
            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }
        })
    }

    private fun initObserver(){
        viewModel.getCountDownFlag().observe(this, Observer {
            if(it){
                countDownTimer.start()
                viewModel.getCountDownFlag().value = false
            }
        })
        viewModel.getStartActivityFlag().observe(this, Observer {
            if(it){
                binding.lottieIntroView.pauseAnimation()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

}