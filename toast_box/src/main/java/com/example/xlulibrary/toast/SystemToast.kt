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
import java.lang.ref.WeakReference
import java.util.*
import kotlin.concurrent.timerTask


/**
 * @ClassName SystemToast
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/5 17:39
 */

@SuppressLint("UseCompatLoadingForDrawables")
class SystemToast : xToast {

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

    private var toast: Toast ?= Toast(ToastBoxRegister.application)

    private var mGravity = 0

    private var mDuration:Int = ToastBoxRegister.SystemToastDuration

    private var clickListener:ToastClickItf ?= null

    private var animation : Animation ?= AnimationUtils.loadAnimation(ToastBoxRegister.application, ToastBoxRegister.anim)

    private var mView : WeakReference<View> ?= null

    private var mMessageView : WeakReference<TextView> ?= null

    private var timer: Timer ?= Timer()



    override fun show() {
        //cancel()
        toast?.duration = mDuration
        mView?.get()?.animation = animation
        toast?.view = mView?.get()
        //ToastBoxRegister.register(this)
        toast?.show()
    }

    override fun cancel() {
        //ToastBoxRegister.unRegister(this)
        clear()
    }

    override fun setText(text: String) {
        mMessageView?.get()?.text = text
    }

    override fun setView(view: View?) {
        mView = WeakReference(view)
        view?.let {
            mMessageView = WeakReference(findMessageView(it))
        }
        timer = Timer()
        timer?.schedule(timerTask {
            this@SystemToast.cancel()
        },3600L)
    }

    override fun getView(): View? {
        return null
    }

    override fun setGravity(location: Location) {
        mGravity = getLocaGravity(location)
        toast?.setGravity(mGravity, x, y)
    }

    override fun getGravity(): Int {
        return mGravity
    }

    override fun setBackDrawable(drawable: Int) {
        mView?.get()?.background = ToastBoxRegister.application.getDrawable(drawable)
    }

    override fun setBackDrawable(drawable: Drawable) {
        mView?.get()?.background = drawable
    }

    override fun getBackDrawable(): Drawable? {
        return mView?.get()?.background
    }

    override fun setTextStyle(@StyleRes style: Int) {
        mMessageView?.get()?.setTextAppearance(style)
    }

    override fun setIcon(drawable: Int?, left: Int, top: Int, right: Int, bottom: Int) {
        if (mView?.get() == null) return
        val icon:ImageView ?= findImageView(mView?.get()!!)

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
        mView?.get()?.alpha = i
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
        clickListener?.setOnToastDismissed()
        toast?.cancel()
        mMessageView = null
        mView = null
        timer?.cancel()
        timer = null
        animation = null
        toast = null
    }


}