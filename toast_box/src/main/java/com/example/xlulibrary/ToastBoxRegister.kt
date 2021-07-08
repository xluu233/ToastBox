package com.example.xlulibrary

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.data.ToastType
import com.example.xlulibrary.toast.ActivityToast
import com.example.xlulibrary.toast.SystemToast
import com.example.xlulibrary.toast.xToast
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
     * 设置系统默认toast动画
     */
    var anim:Int = R.anim.anim_in

    /**
     * 用来保存toastBox实例
     */
    private var _boxStack = WeakReference(LinkedBlockingQueue<xToast>())
    private var boxStack = _boxStack.get()!!

    /**
     * WindowsToast同时最多弹出的数量
     */
    var WindowsToastSize:Int = 3

    /**
     * SystemToast同时弹出的数量
     */
    var SystemToastSize:Int = 1

    /**
     * 系统默认toast时长
     */
    var SystemToastDuration:Int = Toast.LENGTH_SHORT

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
    fun register(xToast: xToast?){
        if (xToast==null) return
        boxStack.offer(xToast)

        when(xToast){
            is SystemToast -> {
                while (boxStack.size > SystemToastSize){
                    val toast = boxStack.poll()
                    toast.cancel()
                }
            }
            is ActivityToast -> {
                while (boxStack.size > WindowsToastSize){
                    val toast = boxStack.poll()
                    toast.cancel()
                }
                /*val lastToast = boxStack.peek()
                if (lastToast?.x== xToast.x &&  lastToast.y==xToast.y  &&  lastToast.getGravity()==xToast.getGravity()){
                    //两者位置相同
                    xToast.y = xToast.y.plus(100)
                }*/
            }
        }
        xLog.d(TAG,"Register    ----  toast_size:${boxStack.size}")
    }

    @Synchronized
    fun unRegister(xToast:xToast?){
        xToast?.let {
            boxStack.remove(it)
        }
        xLog.d(TAG,"unRegister  ----  toast_size:${boxStack.size}")
    }


}
