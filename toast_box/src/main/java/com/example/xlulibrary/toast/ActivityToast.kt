package com.example.xlulibrary.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inspector.WindowInspector
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.WindowLifecycle
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.dp
import com.example.xlulibrary.util.findImageView
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLocalGravity
import java.lang.ref.WeakReference
import java.util.*


class ActivityToast(activity: Activity) : xToast {

    override var x: Int = 0
    override var y: Int = 0
    override var duration: Long = 2500L
    override var isCustomView: Boolean = false

    private var mGravity = 0
    private var anim:Int ?= null
    private var mView:View ?= null
    private var mMessageView:TextView ?= null
    private var clickListener:ToastClickItf ?= null


    private val toast by lazy {
        WindowsMangerToast(this,activity)
    }

    override fun show() {
        ToastBoxRegister.register(this)
        toast.show()
    }

    override fun cancel() {
        ToastBoxRegister.unRegister(this)
        toast.cancel()
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
        mMessageView = findMessageView(mView)
    }

    override fun getView(): View? {
        return mView
    }

    override fun setGravity(location: Location) {
        mGravity = getLocalGravity(location)
    }

    override fun getGravity(): Int {
        return mGravity
    }

    override fun setAnimStyle(style: Int?) {
        this.anim = style
    }

    override fun getAnimStyle(): Int? {
        return anim
    }

    override fun setListener(clickItf: ToastClickItf?) {
        this.clickListener = clickItf
    }

    override fun getListener(): ToastClickItf? {
        return clickListener
    }

    override fun setBackDrawable(@DrawableRes drawable: Int?) {
        if (drawable==null) return
        mView?.background = AppCompatResources.getDrawable(ToastBoxRegister.application,drawable)
    }

    override fun setBackDrawable(drawable: Drawable?) {
        if (drawable==null) return
        mView?.background = drawable
    }

    override fun getBackDrawable(): Drawable? {
        return mView?.background
    }

    override fun setTextStyle(@StyleRes style: Int?) {
        style?.let {
            mMessageView?.setTextAppearance(it)
        }
    }

    override fun setIcon(@DrawableRes drawable: Int?, left: Int, top: Int, right: Int, bottom: Int) {
        val icon: ImageView = findImageView(mView) ?: return

        if (drawable==null){
            icon.visibility = View.GONE
        }else{
            icon.visibility = View.VISIBLE
            icon.setImageDrawable(AppCompatResources.getDrawable(ToastBoxRegister.application,drawable))
            icon.setPadding(left, top, right, bottom)
        }
    }


    override fun setAlpha(i: Float) {
        mView?.alpha = i
    }

    override fun clear() {
        //ViewUtils.gcViews(mView)
        mMessageView = null
        mView = null
    }


}

class WindowsMangerToast(private val xToast: xToast,activity:Activity){

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
        windowLifecycle.register(xToast)
    }

    fun show() {
        if (!mIsShow) {
            //directShow(true)
            //clearCallBack()
            handler.post(showRunnable)
        }
    }

    fun cancel(){
        if (mIsShow){
            //directShow(false)
            //clearCallBack()
            handler.post(cancelRunnable)
        }
    }

    private fun clearCallBack(){
        handler.removeCallbacks(showRunnable)
        handler.removeCallbacks(cancelRunnable)
    }

    private val showRunnable : Runnable = Runnable {
        if (xToast.getView() == null) return@Runnable

        val view = xToast.getView()!!
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            if (WindowInspector.getGlobalWindowViews().contains(view)){
                mWdm.get()?.removeViewImmediate(view)
            }
        }else{
            if(view.isAttachedToWindow){
                mWdm.get()?.removeViewImmediate(view)
            }
        }

        mWdm.get()?.addView(view, mParams)
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                this@WindowsMangerToast.cancel()
            }
        }, xToast.duration)
        mIsShow = true
    }

    private val cancelRunnable : Runnable = Runnable {
        if (xToast.getView() == null) return@Runnable

        val view = xToast.getView()!!
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            if (WindowInspector.getGlobalWindowViews().contains(view)){
                mWdm.get()?.removeViewImmediate(view)
            }
        }else{
            if(view.isAttachedToWindow){
                mWdm.get()?.removeViewImmediate(view)
            }
        }

        xToast.getListener()?.setOnToastDismissed()
        mIsShow = false
        mTimer.cancel()
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
            gravity = xToast.getGravity()
            xToast.getAnimStyle()?.let {
                windowAnimations = it
            }
            this.x = xToast.x
            this.y = xToast.y
        }
    }

}