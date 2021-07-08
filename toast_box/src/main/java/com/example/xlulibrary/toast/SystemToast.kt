package com.example.xlulibrary.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.StyleRes
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.WindowLifecycle
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.ViewUtils
import com.example.xlulibrary.util.findImageView
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLocaGravity
import java.util.*
import kotlin.concurrent.timerTask


/**
 * @ClassName SystemToast
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/5 17:39
 */
class SystemToast() : xToast {

    override var x: Int = 0
    override var y: Int = 0

    override var duration: Long = 3500L
        set(value) {
            field = value
            mDuration = if (value<3500L){
                Toast.LENGTH_SHORT
            }else{
                Toast.LENGTH_LONG
            }
        }

    private var toast: Toast ?= null

    private var mGravity = 0

    private var mDuration:Int = ToastBoxRegister.SystemToastDuration

    private var clickListener:ToastClickItf ?= null

    private var animation : Animation

    private var mView : View ?= null

    private var mMessageView : TextView ?= null

    private var timer: Timer

    //private val windowLifecycle : WindowLifecycle

    init {
        toast = Toast(ToastBoxRegister.application)
        timer = Timer()
        animation = AnimationUtils.loadAnimation(ToastBoxRegister.application, ToastBoxRegister.anim)

        //注册监听生命周期
//        windowLifecycle = if (context is Activity){
//            WindowLifecycle(context)
//        }else{
//            WindowLifecycle(ToastBoxRegister.getActivity())
//        }
//        windowLifecycle.register(this)
    }

    override fun show() {
        toast?.duration = mDuration
        mView?.animation = animation
        toast?.view = mView
        toast?.show()
    }

    override fun cancel() {
        //windowLifecycle.unregister()
        clear()
    }

    override fun setText(text: String) {
        mMessageView?.text = text
    }

    override fun setView(view: View?) {
        mView = view
        mView?.let {
            mMessageView = findMessageView(it)
        }
        timer.schedule(timerTask {
            cancel()
        },3600L)
    }

    override fun getView(): View? {
        return mView
    }

    override fun setGravity(location: Location) {
        mGravity = getLocaGravity(location)
        toast?.setGravity(mGravity, x, y)
    }

    override fun getGravity(): Int {
        return mGravity
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun setBackDrawable(drawable: Int) {
        mView?.background = ToastBoxRegister.application.getDrawable(drawable)
    }

    override fun setBackDrawable(drawable: Drawable) {
        mView?.background = drawable
    }

    override fun getBackDrawable(): Drawable? {
        return mView?.background
    }

    override fun setTextStyle(@StyleRes style: Int) {
        mMessageView?.setTextAppearance(style)
    }

    override fun setIcon(drawable: Int?, left: Int, top: Int, right: Int, bottom: Int) {
        if (mView == null) return
        val icon:ImageView ?= findImageView(mView!!)

        if (drawable==null){
            icon?.visibility = View.GONE
            return
        }
        val _drawable = ToastBoxRegister.application.getDrawable(drawable)
        if (_drawable == null){
            icon?.visibility = View.GONE
        }else{
            icon?.visibility = View.VISIBLE
            icon?.setImageDrawable(_drawable)
            icon?.setPadding(left, top, right, bottom)
        }

    }

    override fun setAlpha(i: Float) {
        mView?.alpha = i
    }


    override fun setAnimStyle(style: Int) {
        //什么也不做
    }

    override fun getAnimStyle(): Int {
        return 0
    }

    override fun setListener(clickItf: ToastClickItf?) {
        this.clickListener = clickItf
    }

    override fun getListener(): ToastClickItf? {
        return clickListener
    }

    override fun clear() {
        //ToastBoxRegister.unRegister(this)
        clickListener?.setOnToastDismissed()
        toast?.cancel()
        ViewUtils.gcViews(mView)
        mMessageView = null
        mView = null
        timer.cancel()
    }


}