package com.example.xlulibrary.toast

import android.annotation.SuppressLint
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
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf
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
class SystemToast(private val context: Context) : xToast {

    override var x: Int = 0
    override var y: Int = 0

    override var duration: Long = 3500L
        set(value) {
            field = value
            if (value<2500){
                mDuration = Toast.LENGTH_SHORT
            }else{
                mDuration = Toast.LENGTH_SHORT
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

    init {
        toast = Toast(context.applicationContext)
        toast?.duration = mDuration
        timer = Timer()
        animation = AnimationUtils.loadAnimation(context, ToastBoxRegister.anim)
    }

    override fun show() {
        mView?.animation = animation
        toast?.view = mView
        toast?.show()
    }

    override fun cancel() {
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
            clear()
        },duration+100)
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
        mView?.background = context.getDrawable(drawable)
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

    @SuppressLint("UseCompatLoadingForDrawables")
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
        ToastBoxRegister.unRegister(this)
        clickListener?.setOnToastDismissed()
        toast?.cancel()
        mMessageView = null
        mView = null
        timer.cancel()
    }


}