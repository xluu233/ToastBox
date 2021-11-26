package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.util.xLog
import java.lang.ref.WeakReference
import java.util.concurrent.LinkedBlockingQueue

object ToastBoxRegister : Initializer<Unit> {

    private val TAG = "ToastBoxRegister"

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


    /**
     * 设置toast字体和背景样式
     */
    var textStyle:TextStyle ?= null

    /**
     * 设置toast默认弹出动画
     */
    var animStyle:Int = R.style.ToastAnim_1


    private var boxStack = LinkedBlockingQueue<xToast>()

    /**
     * WindowsToast同时最多弹出的数量
     */
    var WindowsToastSize:Int = 3


    /**
     * 记录toastBox弹出数量
     */
    @Synchronized
    fun register(xToast: xToast?){
        if (xToast==null) return
        boxStack.offer(xToast)

        while (boxStack.size > WindowsToastSize){
            val toast : xToast ?= boxStack.poll()
            toast?.cancel()
        }
        xLog.d(TAG,"Register    ----  toast_size:${boxStack.size}")
    }

    @Synchronized
    fun unRegister(xToast:xToast?){
        xToast?.apply {
            boxStack.remove(this)
            clear()
        }
        xLog.d(TAG,"unRegister  ----  toast_size:${boxStack.size}")
    }

}
