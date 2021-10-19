package com.luvia.to2youn.ui.main.search

import android.content.Context
import com.luvia.to2youn.network.model.search.UserModel

interface SearchRepository {
    suspend fun requestSearchUsers(context: Context, keyword: String): UserModel
}