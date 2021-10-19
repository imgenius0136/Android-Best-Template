package com.luvia.to2youn.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luvia.to2youn.network.model.search.UserItem
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class PreferenceManager(context: Context) {

    companion object {
        const val HOST_MODE = "HOST_MODE"
        const val BOOKMARK_KEY = "BOOKMARK_KEY"
    }

    private var mPref: SharedPreferences? = null
    private var mPrefBookMark: SharedPreferences? = null

    init {
        mPref = context.getSharedPreferences(context.packageName + "_preference", MODE_PRIVATE)
        mPrefBookMark = context.getSharedPreferences(context.packageName + "bookmark_preference", MODE_PRIVATE)
    }

    fun setHostMode(mode: String){
        mPref!!.edit().putString(HOST_MODE, mode).apply()
    }

    fun getHostMode(): String? {
        return mPref?.getString(HOST_MODE, "")
    }

    fun setBookmarkData(item: UserItem){
        val items: HashSet<UserItem> = getBookmarkData()
        items.add(item)
        val json = Gson().toJson(items)
        mPrefBookMark!!.edit().putString(BOOKMARK_KEY, json).apply()
    }

    fun getBookmarkData(): HashSet<UserItem> {
        val json = mPrefBookMark?.getString(BOOKMARK_KEY, "")
        return if(json != ""){
            Gson().fromJson(json, object : TypeToken<HashSet<UserItem>>() {}.type)
        }else{
            HashSet()
        }
    }

    fun removeBookmarkData(item: UserItem){
        val items: HashSet<UserItem> = Gson().fromJson(mPrefBookMark?.getString(BOOKMARK_KEY, ""), object : TypeToken<HashSet<UserItem>>() {}.type)
        items.remove(item)
        val json = Gson().toJson(items)
        mPrefBookMark!!.edit().putString(BOOKMARK_KEY, json).apply()
    }

}