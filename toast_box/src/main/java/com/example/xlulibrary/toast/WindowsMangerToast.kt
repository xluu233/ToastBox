package com.example.xlulibrary.toast

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.WindowLifecycle
import java.lang.ref.WeakReference
import java.util.*

/**
 * @ClassName ToastImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/29 10:33
 */
class WindowsMangerToast(private val activity: Activity,private val toast: Toast){

    private var mIsShow: Boolean = false
    //private val mTimer: Timer = Timer()
    private var mParams: WindowManager.LayoutParams? = null
    private val windowLifecycle = WindowLifecycle(activity)
    private val handler = Handler(Looper.getMainLooper())

    private val mWdm:WeakReference<WindowManager> by lazy {
        WeakReference(activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    }

    init {
        setParams()
        windowLifecycle.register(toast)
    }

    fun show() {
        if (!mIsShow) {
            handler.post(showRunnable)
        }
    }

    fun cancle(){
        if (mIsShow){
            handler.post(cancleRunnable)
        }
    }

    private val showRunnable : Runnable = Runnable {
        mWdm.get()?.addView(toast.getView(), mParams)//将其加载到windowManager上
/*        mTimer.schedule(object : TimerTask() {
            override fun run() {
                cancle()
            }
        }, toast.getDuration())*/
        mIsShow = true
    }

    private val cancleRunnable : Runnable = Runnable {
        //mWdm.get()?.removeViewImmediate(toast.getView())
        mWdm.get()?.removeView(toast.getView())
        toast.getListener()?.setOnToastDismissed()
        mIsShow = false
        //mTimer.cancel()
        mParams = null
        windowLifecycle.unregister()
    }

    private fun setParams() {
        mParams = WindowManager.LayoutParams()
        mParams?.apply {
            height = WindowManager.LayoutParams.WRAP_CONTENT
            width = WindowManager.LayoutParams.WRAP_CONTENT
            format = PixelFormat.TRANSLUCENT
            flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            gravity = toast.getGravity()
            windowAnimations = toast.getAnim()
            this.x = toast.x
            this.y = toast.y
        }
    }

}