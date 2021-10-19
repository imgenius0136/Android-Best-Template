package com.luvia.to2youn.network

interface HttpCallback<T> {
    fun success(res: T)
    fun fail(message: String)
}