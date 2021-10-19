package com.luvia.to2youn.ui.main

import android.content.Context
import com.luvia.to2youn.network.model.home.HomeDto

interface MainRepository {
    suspend fun requestHome(context: Context): HomeDto.HomeResponse
}