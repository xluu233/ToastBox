package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.example.xlulibrary.toast.Toast
import java.lang.ref.WeakReference

object ToastBoxRegister : ActivityLifecycleCallbacks {


    private var currentActivity: WeakReference<Activity> ?= null

    private var registerActivity:WeakReference<Activity> ?= null

    //private var mToastImpl: WeakReference<Toast> ?= null

    //用来记录不同activity上的toast
    private val toastMap = HashMap<String,MutableList<WeakReference<Toast>>>()

    /**
     * TODO 在app中初始化，监听activity声明周期
     * @param application
     */
    fun init(application: Application){
        application.registerActivityLifecycleCallbacks(this)
    }

    fun getCurrentActivity():Activity?{
        return currentActivity?.get()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        //Log.d(TAG,activity.localClassName)
        currentActivity = WeakReference(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        if (registerActivity == activity) {
            //取消toast
            val list = toastMap[activity.localClassName]
            list?.forEach {
                it.get()?.cancel()
            }
        }
    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        if (registerActivity == activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                registerActivity?.get()?.unregisterActivityLifecycleCallbacks(this)
            } else {
                registerActivity?.get()?.application?.unregisterActivityLifecycleCallbacks(this)
            }
            toastMap.remove(activity.localClassName)
        }
    }

    /**
     * toast注册
     */
    @Synchronized
    fun register(activity: Activity,toast: Toast) {
        if (activity !== registerActivity?.get()){
            registerActivity = WeakReference(activity)

            val list = toastMap[activity.localClassName] ?: mutableListOf()
            list.add(WeakReference(toast))
            toastMap[activity.localClassName] = list

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                registerActivity?.get()?.registerActivityLifecycleCallbacks(this)
            } else {
                registerActivity?.get()?.application?.registerActivityLifecycleCallbacks(this)
            }
        }
    }

    /**
     * toast反注册
     */
    @Synchronized
    fun removeToast(activityName: String,toast: Toast) {
        val list = toastMap[activityName]
        list?.let {
            if (it.contains(WeakReference(toast))){
                it.remove(WeakReference(toast))
            }
            toastMap[activityName] = it
        }
    }

}
