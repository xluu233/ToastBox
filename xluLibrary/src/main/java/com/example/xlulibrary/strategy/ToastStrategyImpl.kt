package com.example.xlulibrary.strategy

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.BaseToast
import com.example.xlulibrary.toast.Toast
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl(private val context: Context) : ToastStrategy{

    private lateinit var toast: Toast
    private var style: ToastStyle = NormalStyle()

    private var clickListener:ToastClickItf ?= null

    override fun setStyle(style: ToastStyle) {
        this.style = style
    }

    override fun setListener(clickItf: ToastClickItf) {
        clickListener = clickItf
    }

    override fun createToast(): Toast {
        val toast = BaseToast(context)
        toast.setView(style.createView(context))
        toast.setGravity(style.location)
        toast.setDuration(style.duration)
        toast.setAnim(style.anim)
        toast.setTextStyle(style.textStyle)
        toast.setBackDrawable(style.backDrawable)
        toast.x = style.x
        toast.y = style.y
        toast.setAlpha(style.alpha)
        toast.setListener(clickListener)
        return toast
    }


    override fun show(text: String) {
        toast = createToast()
        toast.setText(text)
        toast.show()
    }

    override fun cancle() {
        toast.cancel()
    }


}