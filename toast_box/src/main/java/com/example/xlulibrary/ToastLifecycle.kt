package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer
import com.example.xlulibrary.util.xLog
import java.lang.ref.WeakReference

internal object ToastLifecycle : Initializer<Unit> {

    private val TAG = "ToastLifecycle"
    lateinit var application: Application
    private var _currentActivity: WeakReference<Activity> ?= null
    private val currentActivity get() = _currentActivity?.get()
    
    val activityLifecycle = object : Application.ActivityLifecycleCallbacks{
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            _currentActivity = WeakReference(activity)
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityCreated")
        }

        override fun onActivityStarted(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityStarted")
        }

        override fun onActivityResumed(activity: Activity) {
            //activity返回
            _currentActivity = WeakReference(activity)
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityResumed")
        }

        override fun onActivityPaused(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityPaused")
        }

        override fun onActivityStopped(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityStopped")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivitySaveInstanceState")
        }

        override fun onActivityDestroyed(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityDestroyed")
            if (activity.javaClass.name == currentActivity?.javaClass?.name){
                _currentActivity = null
            }
        }
    }

    override fun create(context: Context) {
        application = context as Application
        application.registerActivityLifecycleCallbacks(activityLifecycle)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    fun getActivity():Activity?{
        return currentActivity
    }
    
}
