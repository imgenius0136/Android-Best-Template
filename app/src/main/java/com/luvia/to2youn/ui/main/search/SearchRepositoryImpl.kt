package com.luvia.to2youn.ui.main.search

import android.content.Context
import com.luvia.to2youn.network.HttpCallback
import com.luvia.to2youn.network.HttpClient
import com.luvia.to2youn.network.model.search.UserModel
import retrofit2.Call
import kotlin.coroutines.suspendCoroutine

class SearchRepositoryImpl : SearchRepository{
    override suspend fun requestSearchUsers(context: Context, keyword: String): UserModel {

        val call: Call<UserModel> = HttpClient.getGitHupClient(context).requestSearchUsers(keyword)

        return suspendCoroutine {
            HttpClient.request(call, object : HttpCallback<UserModel> {
                override fun success(res: UserModel) {
                    it.resumeWith(Result.success(res))
                }

                override fun fail(message: String) {
                    it.resumeWith(Result.success(UserModel(0,false, null)))
                }
            })
        }
    }
}