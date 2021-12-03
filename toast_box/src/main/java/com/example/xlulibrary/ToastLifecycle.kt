package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.util.xLog
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.HashMap

internal object ToastLifecycle{

    private val TAG = "ToastLifecycle"
    lateinit var application: Application
    private var _currentActivity: WeakReference<Activity> ?= null
    private val currentActivity get() = _currentActivity?.get()

    private val activityLifecycle = object : Application.ActivityLifecycleCallbacks{
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
            clearActivityToast(activity.javaClass.simpleName)
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

    fun init(context: Context){
        application = context as Application
        application.registerActivityLifecycleCallbacks(activityLifecycle)
    }

    fun getActivity():Activity?{
        return currentActivity
    }

    fun getCurrentTAG():String?{
        return currentActivity?.javaClass?.simpleName
    }


    private val boxMap = HashMap<String,LinkedList<xToast>>()

    @Synchronized
    internal fun register(xToast: xToast?){
        val tag = xToast?.TAG
        if (xToast==null || tag.isNullOrEmpty()) return

        val linkedList = boxMap[tag] ?: LinkedList()
        linkedList.offer(xToast)

        while (linkedList.size > ToastBox.maxToastSize){
            val toast : xToast ?= linkedList.poll()
            toast?.cancel()
        }
        boxMap[tag] = linkedList
        xLog.d(TAG,"Register    ----  tag:${tag} , toast_size:${linkedList.size}")
    }

    @Synchronized
    internal fun unRegister(xToast:xToast?){
        val tag = xToast?.TAG
        if (xToast==null || tag.isNullOrEmpty()) return
        val linkedList = boxMap[tag] ?: LinkedList()

        xToast.apply {
            clear()
            linkedList.remove(this)
        }
        boxMap[tag] = linkedList
        xLog.d(TAG,"unRegister    ----  tag:${tag} , toast_size:${linkedList.size}")
    }

    /**
     * 在Activity跳转的时候，删除上一个Activity中的toast
     */
    fun clearActivityToast(tag:String){
        boxMap[tag]?.forEach {
            xLog.d(TAG, "clearActivityToast: ")
            it.cancel()
        }
    }

}
