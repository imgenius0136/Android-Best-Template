package com.luvia.to2youn.network.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class BaseResponse {

    @SerializedName("code")
    @Expose
    val code: Int = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

    override fun toString(): String {
        return "BaseResponse(code=$code, message=$message)"
    }

}