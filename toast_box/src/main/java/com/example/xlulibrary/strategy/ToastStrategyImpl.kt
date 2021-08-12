package com.example.xlulibrary.strategy

import android.view.View
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.ActivityToast
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl : ToastStrategy{

//    private var _view: WeakReference<View> ?= null
//    private val view get() = _view?.get()

//    private var _toast: WeakReference<xToast> ?= null
//    private val toast get() = _toast?.get()

    private var view:View ?= null
    private var toast:xToast ?= null

    private var style : ToastStyle = NormalStyle()
    private var clickListener:ToastClickItf ?= null


    override fun setStyle(style: ToastStyle) {
        this.style = style
    }

    override fun setListener(clickItf: ToastClickItf) {
        clickListener = clickItf
    }

    private var useCustomView:Boolean = false

    @Synchronized
    override fun setView(view: View) {
        useCustomView = true
        this.view = view
    }

    @Synchronized
    override fun createToast(): xToast {
        val toast = ActivityToast()

        if (useCustomView && view!=null){
            //自定义View
            toast.setView(view)
            useCustomView = false
        }else{
            toast.setView(style.createView())
            toast.setTextStyle(style.textStyle)
            toast.setBackDrawable(style.backDrawable)
            toast.setIcon(style.iconDrawable,style.left, style.top, style.right, style.bottom)
        }
        toast.x = style.x
        toast.y = style.y
        toast.duration = style.duration
        toast.setGravity(style.location)
        toast.setAnimStyle(style.animStyle)
        toast.setAlpha(style.alpha)
        toast.setListener(clickListener)
        return toast
    }

    override fun getIToast(): xToast? {
        return toast
    }

    override fun show(text: String) {
        toast = createToast()
        toast?.setText(text)
        toast?.show()
    }

    override fun cancle() {
        toast?.cancel()
        view = null
        toast = null
    }


}