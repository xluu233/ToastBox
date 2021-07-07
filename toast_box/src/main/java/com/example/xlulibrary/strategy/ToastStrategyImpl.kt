package com.example.xlulibrary.strategy

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.ToastType
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.ActivityToast
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import com.example.xlulibrary.toast.SystemToast
import java.lang.ref.WeakReference

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl : ToastStrategy{


    private var _view: WeakReference<View> ?= null
    private val view get() = _view?.get()

    private var _toast: WeakReference<xToast> ?= null
    private val toast get() = _toast?.get()

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
        _view = WeakReference(view)
    }

    private var iconDrawable:Int ?= null
    override fun setIcon(drawable: Int?) {
        this.iconDrawable = drawable
    }

    @Synchronized
    override fun createToast(context:Context): xToast {
        val toast = when(ToastBoxRegister.toastType){
            ToastType.SystemToast -> {
                SystemToast(context)
            }
            ToastType.WindowsToast -> {
                if (context is Activity){
                    ActivityToast(context as Activity)
                }else{
                    ActivityToast(ToastBoxRegister.getActivity())
                }
            }
        }
        if (useCustomView && view!=null){
            //自定义View
            toast.setView(view)
            useCustomView = false
        }else{
            toast.setView(style.createView(ToastBoxRegister.getContext()))
            toast.setTextStyle(style.textStyle)
            toast.setBackDrawable(style.backDrawable)
            ToastBoxRegister.defaultIcon?.let { toast.setIcon(it) }
            toast.setIcon(iconDrawable)
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

    override fun show(context:Context,text: String) {
        _toast = WeakReference(createToast(context))
        toast?.setText(text)
        ToastBoxRegister.register(toast)
        //toast?.show()
    }

    override fun cancle() {
        toast?.cancel()
        _view = null
        _toast = null
    }


}