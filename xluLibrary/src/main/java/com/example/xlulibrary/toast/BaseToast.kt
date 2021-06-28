package com.example.xlulibrary.toast

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLocaGravity
import java.lang.ref.WeakReference
import java.util.*
import java.util.logging.Handler

/**
 * @ClassName ToastImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 17:10
 */
class BaseToast(private val context: Context) : Toast {

    override var x: Int = 0
    override var y: Int = 0

    /** Toast 显示重心  */
    var mGravity = 0

    /** Toast 显示时长  */
    var mDuration = 0L

    /** Toast 布局  */
    var mView: View? = null

    /*动画*/
    var anim:Int ?= null

    /** Toast 消息 View  */
    private var mMessageView: TextView? = null

    /*事件监听*/
    private var clickListener:ToastClickItf ?= null

    private val windowToast by lazy {
        AnimToast(context,this)
    }

    override fun show() {
        windowToast.show()
    }

    override fun cancel() {
        windowToast.cancle()
        clickListener?.setOnToastDismissed()
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
        mMessageView?.setOnClickListener {
            clickListener?.setOnTextClicked()
        }
    }

    override fun getView(): View? {
        return mView
    }

    override fun setDuration(duration: Long) {
        mDuration = duration
    }

    override fun getDuration(): Long {
        return mDuration
    }

    override fun setGravity(location: Location) {
        mGravity = getLocaGravity(location)
    }

    override fun getGravity(): Int {
        return mGravity
    }


    override fun setAnim(anim: Int) {
        this.anim = anim
    }

    override fun getAnim(): Int {
        return anim!!
    }

    override fun setListener(clickItf: ToastClickItf?) {
        this.clickListener = clickItf
    }

    override fun getListener(): ToastClickItf? {
        return clickListener
    }

    override fun setBackDrawable(drawable: Int) {
        mView?.background = context.getDrawable(drawable)
    }

    override fun setBackDrawable(drawable: Drawable) {
        mView?.background = drawable
    }

    override fun getBackDrawable(): Drawable? {
        return mView?.background
    }

    override fun setTextStyle(style: Int) {
        mMessageView?.setTextAppearance(style)
    }

    override fun setAlpha(i: Float) {
        mView?.alpha = i
    }



}

class AnimToast(context: Context,toast: Toast){

    private val mWdm: WindowManager
    private var mIsShow: Boolean = false
    private val mTimer: Timer
    private var mParams: WindowManager.LayoutParams? = null
    private var toast: Toast

    init {
        mIsShow = false
        mWdm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mTimer = Timer()
        this.toast = toast
        setParams()
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



    fun show() {
        if (!mIsShow) {//如果Toast没有显示，则开始加载显示
            mIsShow = true
            mWdm.addView(toast.getView(), mParams)//将其加载到windowManager上
            mTimer.schedule(object : TimerTask() {
                override fun run() {
                    cancle()
                }
            }, toast.getDuration())
        }
    }

    fun cancle() {
        mWdm.removeView(toast.getView())
        toast.getListener()?.setOnToastDismissed()
        mIsShow = false
        mTimer.cancel()
    }

}