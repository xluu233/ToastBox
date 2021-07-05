package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.data.ToastType
import com.example.xlulibrary.toast.Toast
import com.example.xlulibrary.util.xLog
import java.lang.ref.WeakReference
import java.util.concurrent.LinkedBlockingQueue

object ToastBoxRegister : ActivityLifecycleCallbacks {

    private val TAG = "ToastBoxRegister"

    private var currentActivity: WeakReference<Activity> ?= null


    lateinit var application: Application

    var defaultIcon : Int ?= null
    /**
     * 设置toast默认实现方式
     */
    var toastType:ToastType = ToastType.WindowsToast

    /**
     * 设置toast字体和背景样式
     */
    var textStyle:TextStyle ?= null

    /**
     * 设置toast默认弹出动画
     */
    var animStyle:Int = R.style.ToastAnim_1

    /**
     * 用来保存toastBox实例
     */
    private var _boxStack = WeakReference(LinkedBlockingQueue<Toast>())
    private var boxStack = _boxStack.get()!!

    /**
     * 同时最多弹出的toast数量
     */
    private var stackSize:Int = 3

    /**
     * 在app中初始化，监听activity声明周期
     */
    fun init(application: Application):ToastBoxRegister = apply{
        application.registerActivityLifecycleCallbacks(this)
        this@ToastBoxRegister.application = application
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


    /**
     * 记录toastBox弹出数量
     */
    @Synchronized
    fun register(toast: Toast?){
        xLog.d(TAG,"register")
        toast?.let {
            boxStack.offer(it)
        }
        while (boxStack.size > stackSize){
            xLog.d(TAG,"POLL")
            val toast1 = boxStack.poll()
            toast1.cancel()
            xLog.d(TAG,"toast_size:${boxStack.size}")
        }
    }

    fun unRegister(toast:Toast?){
        xLog.d(TAG,"unRegister")
        toast?.let {
            boxStack.remove(it)
        }
    }


}
