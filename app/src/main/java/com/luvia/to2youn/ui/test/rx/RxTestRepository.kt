package com.luvia.to2youn.ui.test.rx

import android.content.Context
import com.luvia.to2youn.network.model.home.HomeDto
import io.reactivex.rxjava3.core.Observable

interface RxTestRepository {
    suspend fun requestHome(context: Context): Observable<HomeDto.HomeResponse>
}