package com.example.xlulibrary

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.example.xlulibrary.ToastLifecycle.activityLifecycle
import com.example.xlulibrary.ToastLifecycle.application
import com.example.xlulibrary.util.xLog

/**
 * @ClassName ToastInit
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/12/13 11:33
 */
class ToastInit : Initializer<Unit> {
    private val TAG = "ToastInit"

    override fun create(context: Context) {
        application = context as Application
        application.registerActivityLifecycleCallbacks(activityLifecycle)
        xLog.d(TAG,"startup-init")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}