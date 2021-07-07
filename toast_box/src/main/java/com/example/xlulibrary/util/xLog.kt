package com.example.xlulibrary.util

import android.util.Log

/**
 * @ClassName LogUtils
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/2 18:01
 */
object xLog {

    var sLogName = "ToastBox"
    var sIsShow = true

    fun init(logName: String, isShow: Boolean) {
        sLogName = logName
        sIsShow = isShow
    }

    fun d(TAG: String, content: String?) {
        if (sIsShow) {
            Log.d("[$sLogName]$TAG", content!!)
        }
    }

    fun v(TAG: String, content: String?) {
        if (sIsShow) {
            Log.v("[$sLogName]$TAG", content!!)
        }
    }

    fun w(TAG: String, content: String?) {
        if (sIsShow) {
            Log.w("[$sLogName]$TAG", content!!)
        }
    }

    fun e(TAG: String, content: String?) {
        if (sIsShow) {
            Log.e("[$sLogName]$TAG", content!!)
        }
    }

    fun i(TAG: String, content: String?) {
        if (sIsShow) {
            Log.i("[$sLogName]$TAG", content!!)
        }
    }

    fun d(content: String?) {
        if (sIsShow) {
            Log.d("[$sLogName]", content!!)
        }
    }

    fun v(content: String?) {
        if (sIsShow) {
            Log.v("[$sLogName]", content!!)
        }
    }

    fun w(content: String?) {
        if (sIsShow) {
            Log.w("[$sLogName]", content!!)
        }
    }

    fun e(content: String?) {
        if (sIsShow) {
            Log.e("[$sLogName]", content!!)
        }
    }

    fun i(content: String?) {
        if (sIsShow) {
            Log.i("[$sLogName]", content!!)
        }
    }


}