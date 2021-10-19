package com.luvia.to2youn.ui.test.rx

import androidx.activity.viewModels
import com.google.gson.Gson
import com.luvia.to2youn.base.BaseMvvmActivity
import com.luvia.to2youn.base.BaseViewModel
import com.luvia.to2youn.databinding.ActivityRxTestBinding
import com.luvia.to2youn.network.HttpClient
import com.luvia.to2youn.network.model.home.HomeDto
import com.luvia.to2youn.utils.BlueUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.collections.ArrayList

class RxTestActivity : BaseMvvmActivity<ActivityRxTestBinding>() {

    private val viewModel: RxTestViewModel by viewModels()

    override fun getViewBinding(): ActivityRxTestBinding {
        return ActivityRxTestBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun init() {
        initObserver()
        requestHomRx()
    }

    private fun initObserver(){

    }

    private fun requestHomRx(){

        val tasks = ArrayList<Observable<HomeDto.HomeResponse>>()
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_1", 1)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_2", 2)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_5",5)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_4", 4)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_6",6)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_9",9)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_3",3)))
        tasks.add(HttpClient.getClient(this).requestHomeRx(HomeDto.HomeBody("job_0", 0)))

        BlueUtil.d("start")

        tasks[0]
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    BlueUtil.d("onNext :: " + Gson().toJson(it))
                },
                {
                    BlueUtil.d("Throwable")
                },
                {
                    BlueUtil.d("onComplete")
                }
            )


//        Observable.zip(tasks,
//            Function {
//                BlueUtil.d("function :: " + Gson().toJson(it))
//            }
//        ).subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    BlueUtil.d("onNext :: " + Gson().toJson(it))
//                },{
//                    BlueUtil.d("onError :: $it")
//                },{
//                    BlueUtil.d("onComplete :: ")
//                }
//            )

    }


}