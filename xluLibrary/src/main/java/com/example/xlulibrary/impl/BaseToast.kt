package com.example.xlulibrary.impl

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.example.xlulibrary.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.getLocaGravity
import com.example.xlulibrary.itf.Toast
import java.util.*

/**
 * @ClassName ToastImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 17:10
 */
class BaseToast(activity: Activity) : Toast {

    /** Toast 显示重心  */
    private var mGravity = 0

    /** Toast 显示时长  */
    private var mDuration = 0

    /** 水平偏移  */
    private var mXOffset = 0

    /** 垂直偏移  */
    private var mYOffset = 0

    /** 水平间距  */
    private var mHorizontalMargin = 0f

    /** 垂直间距  */
    private var mVerticalMargin = 0f

    /** Toast 布局  */
    private var mView: View? = null

    /** Toast 消息 View  */
    private var mMessageView: TextView? = null


    private val windowToast by lazy {
        AnimToast(activity, this)
    }

    override fun show() {
        windowToast.show()
    }

    override fun cancel() {
        windowToast.cancle()
    }

    override fun setText(text: String) {
        mMessageView?.text = text
    }

    override fun setView(view: View) {
        mView = view
        if (mView==null){
            mMessageView = null
            return
        }
        mMessageView = findMessageView(view)
    }

    override fun getView(): View? {
        return mView
    }

    override fun setDuration(duration: Int) {
        mDuration = duration
    }

    override fun getDuration(): Int {
        return mDuration
    }

    override fun setGravity(location: Location, xOffset: Int, yOffset: Int) {
        mGravity = getLocaGravity(location)
        mXOffset = xOffset
        mYOffset = yOffset
    }

    override fun getGravity(): Int {
        return mGravity
    }

    override fun getXOffset(): Int {
        return mXOffset
    }

    override fun getYOffset(): Int {
        return mYOffset
    }

    override fun setMargin(horizontalMargin: Float, verticalMargin: Float) {
        mHorizontalMargin = horizontalMargin
        mVerticalMargin = verticalMargin
    }

    override fun getHorizontalMargin(): Float {
        return mHorizontalMargin
    }

    override fun getVerticalMargin(): Float {
        return mVerticalMargin
    }


}

class AnimToast(activity: Activity, toast: Toast){

    private val mWdm: WindowManager
    private var mIsShow: Boolean = false
    private val mTimer: Timer
    private var mParams: WindowManager.LayoutParams? = null
    private var toast:Toast

    init {
        mIsShow = false//记录当前Toast的内容是否已经在显示
        mWdm = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mTimer = Timer()
        this.toast = toast
        setParams()
    }

    private fun setParams() {
        mParams = WindowManager.LayoutParams()
        mParams!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        mParams!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        mParams!!.format = PixelFormat.TRANSLUCENT
        mParams!!.windowAnimations = R.style.MiuiToast//设置进入退出动画效果
        mParams!!.flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        mParams!!.gravity = toast.getGravity()
        mParams!!.y = toast.getYOffset()
        mParams!!.x = toast.getXOffset()
        mParams!!.horizontalMargin = toast.getHorizontalMargin()
        mParams!!.verticalMargin = toast.getVerticalMargin()
    }


    fun show() {
        if (!mIsShow) {//如果Toast没有显示，则开始加载显示
            mIsShow = true
            mWdm.addView(toast.getView(), mParams)//将其加载到windowManager上
            mTimer.schedule(object : TimerTask() {
                override fun run() {
                    mWdm.removeView(toast.getView())
                    mIsShow = false
                }
            }, toast.getDuration().toLong())
        }
    }

    fun cancle(){
        mTimer.cancel()
        mWdm.removeView(toast.getView())
    }


}