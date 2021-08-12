package com.example.xlulibrary.util

import android.util.Log

/**
 * @ClassName LogUtils
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/2 18:01
 */
object xLog {

    private var LogName = "ToastBox"
    private var showLog = false

    fun init(logName: String, showLog: Boolean) {
        LogName = logName
        this.showLog = showLog
    }

    fun d(TAG: String, content: String?) {
        if (showLog) {
            Log.d("[$LogName]$TAG", content!!)
        }
    }

    fun v(TAG: String, content: String?) {
        if (showLog) {
            Log.v("[$LogName]$TAG", content!!)
        }
    }

    fun w(TAG: String, content: String?) {
        if (showLog) {
            Log.w("[$LogName]$TAG", content!!)
        }
    }

    fun e(TAG: String, content: String?) {
        if (showLog) {
            Log.e("[$LogName]$TAG", content!!)
        }
    }

    fun i(TAG: String, content: String?) {
        if (showLog) {
            Log.i("[$LogName]$TAG", content!!)
        }
    }

    fun d(content: String?) {
        if (showLog) {
            Log.d("[$LogName]", content!!)
        }
    }

    fun v(content: String?) {
        if (showLog) {
            Log.v("[$LogName]", content!!)
        }
    }

    fun w(content: String?) {
        if (showLog) {
            Log.w("[$LogName]", content!!)
        }
    }

    fun e(content: String?) {
        if (showLog) {
            Log.e("[$LogName]", content!!)
        }
    }

    fun i(content: String?) {
        if (showLog) {
            Log.i("[$LogName]", content!!)
        }
    }


}