package com.luvia.to2youn.network.model.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.luvia.to2youn.network.model.base.BaseResponse
import kotlinx.parcelize.Parcelize

class HomeDto {

    @Parcelize
    data class HomeResponse(
        @SerializedName("result")
        @Expose
        val result: HomeResult?
    ): Parcelable

    @Parcelize
    data class HomeResult(
        @SerializedName("health")
        @Expose
        val health: Boolean,
        @SerializedName("jobId")
        @Expose
        val jobId: String
    ) : Parcelable

    @Parcelize
    data class HomeBody(
        val jobId: String?,
        val sec: Int
    ): Parcelable

}