package com.example.xlulibrary.util

import android.util.Log

internal object xLog {

    private var LogName = "ToastBox"
    private var showLog = false

    fun init(logName: String, showLog: Boolean) {
        LogName = logName
        this.showLog = showLog
    }

    fun d(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.d("[$LogName]$TAG", content)
        }
    }

    fun v(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.v("[$LogName]$TAG", content)
        }
    }

    fun w(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.w("[$LogName]$TAG", content)
        }
    }

    fun e(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.e("[$LogName]$TAG", content)
        }
    }

    fun i(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.i("[$LogName]$TAG", content)
        }
    }

    fun d(content: String?) {
        if (showLog && content!=null) {
            Log.d("[$LogName]", content)
        }
    }

    fun v(content: String?) {
        if (showLog && content!=null) {
            Log.v("[$LogName]", content)
        }
    }

    fun w(content: String?) {
        if (showLog && content!=null) {
            Log.w("[$LogName]", content)
        }
    }

    fun e(content: String?) {
        if (showLog && content!=null) {
            Log.e("[$LogName]", content)
        }
    }

    fun i(content: String?) {
        if (showLog && content!=null) {
            Log.i("[$LogName]", content)
        }
    }


}