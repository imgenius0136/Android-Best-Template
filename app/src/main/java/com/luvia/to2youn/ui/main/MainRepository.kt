package com.luvia.to2youn.ui.main

import android.content.Context
import com.luvia.to2youn.network.model.home.HomeDto

//테스트를 위해 만들어둔 레포지토리 이다.
interface MainRepository {
    suspend fun requestHome(context: Context): HomeDto.HomeResponse
}