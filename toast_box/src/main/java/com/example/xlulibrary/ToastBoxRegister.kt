package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.data.ToastType
import com.example.xlulibrary.toast.Toast
import java.lang.ref.WeakReference

object ToastBoxRegister : ActivityLifecycleCallbacks {

    private var currentActivity: WeakReference<Activity> ?= null

    var toastType:ToastType = ToastType.WindowsToast
    var textStyle:TextStyle = TextStyle.White

    /**
     * TODO 在app中初始化，监听activity声明周期
     * @param application
     */
    fun init(application: Application):ToastBoxRegister = apply{
        application.registerActivityLifecycleCallbacks(this)
    }



    fun getActivity():Activity{
        return currentActivity?.get()!!
    }

    fun getContext():Context{
        return currentActivity?.get()?.baseContext!!
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity){
            currentActivity = null
        }
    }

}
