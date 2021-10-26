package com.luvia.to2youn.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luvia.to2youn.network.model.github.UserItem
import kotlin.collections.HashSet

class PreferenceManager(context: Context) {

    companion object {
        const val HOST_MODE = "HOST_MODE"
    }

    private var mPref: SharedPreferences? = null

    init {
        mPref = context.getSharedPreferences(context.packageName + "_preference", MODE_PRIVATE)
    }

    fun setHostMode(mode: String){
        mPref!!.edit().putString(HOST_MODE, mode).apply()
    }

    fun getHostMode(): String? {
        return mPref?.getString(HOST_MODE, "")
    }

}