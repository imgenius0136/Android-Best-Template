package com.luvia.to2youn.utils

import android.util.Log

//로그 유틸이다. 작성자의 영어 이름을 따서 만들었다.
//***"현업에선 이렇게 쓰지 않습니다."***
class BlueUtil {
    companion object {
        private const val TAG = "blue ++ "

        fun d(msg: String) {
            Log.d(TAG, msg)
        }
    }
}