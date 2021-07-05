package com.example.xlulibrary.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.WindowLifecycle
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.ViewUtils
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLocaGravity
import java.lang.ref.WeakReference
import java.util.*

/**
 * @ClassName ToastImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 17:10
 */
class ActivityToast(private val context: Context) : Toast {

    override var x: Int = 0
    override var y: Int = 0

    /** Toast 显示重心  */
    var mGravity = 0

    /** Toast 显示时长  */
    var mDuration = 0L

    /*动画*/
    var anim:Int ?= null

    private var mView:View ?= null
    private var mMessageView : TextView ?= null

    /*事件监听*/
    private var clickListener:ToastClickItf ?= null


    private val toast by lazy {
        WindowsMangerToast(context as Activity,this)
    }

    override fun show() {
        toast.show()
    }

    override fun cancel() {
        toast.cancle()
        ToastBoxRegister.unRegister(this)
    }

    override fun setText(text: String) {
        mMessageView?.text = text
    }

    override fun setView(view: View?) {
        mView = view
        if (mView==null){
            mMessageView = null
            return
        }
        mMessageView = findMessageView(mView!!)
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

    @SuppressLint("UseCompatLoadingForDrawables")
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

    override fun setIcon(drawable: Int?) {
        val icon = mView?.findViewById<ImageView>(R.id.default_icon) as ImageView
        if (drawable==null){
            icon.visibility = View.GONE
            return
        }
        val _drawable = context.getDrawable(drawable)
        if (_drawable == null){
            icon.visibility = View.GONE
        }else{
            icon.visibility = View.VISIBLE
            icon.setImageDrawable(_drawable)
        }
    }

    override fun setAlpha(i: Float) {
        mView?.alpha = i
    }

    override fun clear() {
        ViewUtils.gcViews(mView)
        mMessageView = null
        mView = null
    }


}


class WindowsMangerToast(private val activity: Activity,private val toast: Toast){

    private var mIsShow: Boolean = false
    private val mTimer: Timer = Timer()
    private var mParams: WindowManager.LayoutParams? = null
    private val windowLifecycle = WindowLifecycle(activity)
    private val handler = Handler(Looper.getMainLooper())

    private val mWdm: WeakReference<WindowManager> by lazy {
        WeakReference(activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    }

    init {
        setParams()
        windowLifecycle.register(toast)
    }

    fun show() {
        if (!mIsShow) {
            //directShow(true)
            clearCallBack()
            handler.post(showRunnable)
        }
    }

    fun cancle(){
        if (mIsShow){
            //directShow(false)
            clearCallBack()
            handler.post(cancleRunnable)
        }
    }

    private fun clearCallBack(){
        handler.removeCallbacks(showRunnable)
        handler.removeCallbacks(cancleRunnable)
    }

    private val showRunnable : Runnable = Runnable {
        while (toast.getView()?.parent != null){
            mWdm.get()?.removeViewImmediate(toast.getView())
        }
        mWdm.get()?.addView(toast.getView(), mParams)//将其加载到windowManager上
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                cancle()
            }
        }, toast.getDuration())
        mIsShow = true
    }

    private val cancleRunnable : Runnable = Runnable {
/*        if (toast.getView()?.parent != null){
            mWdm.get()?.removeView(toast.getView())
        }*/
        //mWdm.get()?.removeViewImmediate(toast.getView())
        mWdm.get()?.removeView(toast.getView())
        toast.getListener()?.setOnToastDismissed()
        mIsShow = false
        mTimer.cancel()
        mParams = null
        windowLifecycle.unregister()
    }


    private fun directShow(show:Boolean){
        activity.runOnUiThread {
            if (show){
                mWdm.get()?.addView(toast.getView(), mParams)//将其加载到windowManager上
                mTimer.schedule(object : TimerTask() {
                    override fun run() {
                        cancle()
                    }
                }, toast.getDuration())
                mIsShow = true
            }else{
                mWdm.get()?.removeView(toast.getView())
                toast.getListener()?.setOnToastDismissed()
                mIsShow = false
                mTimer.cancel()
                mParams = null
                windowLifecycle.unregister()
            }
        }
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