package com.luvia.to2youn.network

import com.luvia.to2youn.network.model.home.HomeDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/home")
    fun requestHome(): Call<HomeDto.HomeResponse>

    @POST("/rx_home")
    fun requestHomeRx(@Body body: HomeDto.HomeBody): Observable<HomeDto.HomeResponse>
}