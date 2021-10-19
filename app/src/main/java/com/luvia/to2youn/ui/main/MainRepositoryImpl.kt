package com.luvia.to2youn.ui.main

import android.content.Context
import com.luvia.to2youn.network.HttpCallback
import com.luvia.to2youn.network.HttpClient
import com.luvia.to2youn.network.model.home.HomeDto
import retrofit2.Call
import kotlin.coroutines.suspendCoroutine

class MainRepositoryImpl : MainRepository {

    override suspend fun requestHome(context: Context): HomeDto.HomeResponse {

        val call: Call<HomeDto.HomeResponse> = HttpClient.getClient(context).requestHome()

        return suspendCoroutine {
            HttpClient.request(call, object : HttpCallback<HomeDto.HomeResponse> {
                override fun success(res: HomeDto.HomeResponse) {
                    it.resumeWith(Result.success(res))
                }

                override fun fail(message: String) {

                }
            })
        }
    }

}