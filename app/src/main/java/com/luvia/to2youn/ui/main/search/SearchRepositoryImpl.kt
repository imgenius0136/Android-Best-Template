package com.luvia.to2youn.ui.main.search

import android.content.Context
import com.luvia.to2youn.network.HttpCallback
import com.luvia.to2youn.network.HttpClient
import com.luvia.to2youn.network.model.search.UserModel
import retrofit2.Call
import kotlin.coroutines.suspendCoroutine

//검색 interface 구현체
//뷰모델 스코프에서 throw error 를 캐치가 안되는 이슈때문에 모두 success 를 보내주었다.
//개선 시 live 데이터 자체를 리턴하는 방식으로 레포지토리를 구성하고자 한다.
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