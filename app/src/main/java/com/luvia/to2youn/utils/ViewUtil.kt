package com.luvia.to2youn.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

//뷰의 크기 계산 ex) px -> dp and dp -> px etc ... , 키보드등 을 컨트롤 하기위한 유틸이다.
class ViewUtil {
    companion object {
        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }
}