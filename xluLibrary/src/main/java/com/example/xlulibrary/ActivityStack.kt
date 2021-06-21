package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle


class ActivityStack : ActivityLifecycleCallbacks {


    var foregroundActivity: Activity? = null

    companion object {
        fun register(application: Application): ActivityStack {
            val lifecycle = ActivityStack()
            application.registerActivityLifecycleCallbacks(lifecycle)
            return lifecycle
        }
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}


    override fun onActivityResumed(activity: Activity) {
        foregroundActivity = activity
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