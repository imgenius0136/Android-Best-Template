package com.luvia.to2youn.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.luvia.to2youn.R
import com.luvia.to2youn.utils.BlueUtil

abstract class BaseMvvmActivity<T> : AppCompatActivity() where T : ViewBinding {

    lateinit var binding : T

    abstract fun getViewBinding() : T
    abstract fun getViewModel() : BaseViewModel
    abstract fun init()

    private val loadingView : FrameLayout by lazy {
        FrameLayout(this)
    }
    private val animationView : LottieAnimationView by lazy {
        LottieAnimationView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()

        setContentView(binding.root)

        initProgressView()
        initProgressObserve()

        init()
    }

    private fun initProgressView(){
        loadingView.visibility = View.GONE
        loadingView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_loading_background))
        loadingView.isClickable = true
        loadingView.isFocusable = true

        animationView.layoutParams = FrameLayout.LayoutParams((resources.displayMetrics.density * 110).toInt(), (resources.displayMetrics.density * 110).toInt())
        (animationView.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        animationView.setAnimation("loading.json")
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.repeatCount = LottieDrawable.INFINITE

        loadingView.addView(animationView)
        addContentView(loadingView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    private fun initProgressObserve(){
        getViewModel().progress.observe(this, Observer {
            if(it){
                animationView.playAnimation()
                loadingView.visibility = View.VISIBLE
            }else{
                animationView.pauseAnimation()
                loadingView.visibility = View.GONE
            }
        })
    }

    open fun showProgress(){
        BlueUtil.d("progress start")
        getViewModel().progress.value = true
    }

    open fun hideProgress(){
        BlueUtil.d("progress hide")
        getViewModel().progress.value = false
    }

}