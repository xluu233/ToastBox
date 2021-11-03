package com.example.xlulibrary

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Build
import android.os.Bundle
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.util.xLog


class WindowLifecycle(private val activity: Activity) : ActivityLifecycleCallbacks {

    private val TAG by lazy {
        activity.localClassName
    }

    private var xToast : xToast ?= null
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}


    override fun onActivityPaused(activity: Activity) {
        xLog.d(TAG,"onActivityPaused")
        xToast?.cancel()
    }

    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        unregister()
    }

    fun register(xToast: xToast?) {
        this.xToast = xToast
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.registerActivityLifecycleCallbacks(this)
        } else {
            activity.application.registerActivityLifecycleCallbacks(this)
        }
    }

    fun unregister() {
        xToast?.clear()
        xToast = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.unregisterActivityLifecycleCallbacks(this)
        } else {
            activity.application.unregisterActivityLifecycleCallbacks(this)
        }
    }
}