package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import java.lang.ref.WeakReference


object ToastBoxRegister : ActivityLifecycleCallbacks {


    private var foregroundActivity: WeakReference<Activity>? = null


    fun init(application: Application){
        application.registerActivityLifecycleCallbacks(this)
    }

    fun getCurrentActivity():Activity{
        return foregroundActivity?.get()!!
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}


    override fun onActivityResumed(activity: Activity) {
        foregroundActivity = WeakReference(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        if (foregroundActivity !== activity) {
            return
        }
        foregroundActivity = null
    }

    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}


}