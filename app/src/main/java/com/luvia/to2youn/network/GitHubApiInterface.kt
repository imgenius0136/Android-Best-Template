package com.luvia.to2youn.network

import com.luvia.to2youn.network.model.search.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiInterface {
    @GET("/search/users")
    fun requestSearchUsers(
        @Query("q") keyword: String,
        @Query("per_page") perPage: Int? = 100,
        @Query("page") page: Int = 1): Call<UserModel>
}