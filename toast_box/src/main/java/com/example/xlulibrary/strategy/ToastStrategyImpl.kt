package com.example.xlulibrary.strategy

import android.app.Activity
import android.content.Context
import android.view.View
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.ToastType
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.ActivityToast
import com.example.xlulibrary.toast.Toast
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import java.lang.ref.WeakReference

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl(private val context: Context) : ToastStrategy{

    private var _toast: WeakReference<Toast> ?= null
    private val toast get() = _toast?.get()
    private var _style : ToastStyle ?= NormalStyle()
    private val style : ToastStyle get() = _style!!

    override var view: View? = null

    private var clickListener:ToastClickItf ?= null

    override fun setStyle(style: ToastStyle) {
        this._style = style
    }

    override fun setListener(clickItf: ToastClickItf) {
        clickListener = clickItf
    }

    override fun createToast(): Toast {
        val toast = when(ToastBoxRegister.toastType){
            ToastType.SystemToast -> {
                ActivityToast(context)
            }
            ToastType.WindowsToast,ToastType.WindowsToastImage -> {
                ActivityToast(context)
            }
            else -> {
                ActivityToast(context)
            }
        }
        if (view==null){
            toast.setView(style.createView(context))
            toast.setTextStyle(style.textStyle)
            toast.setBackDrawable(style.backDrawable)
        }else{
            //自定义View
            toast.setView(view!!)
        }
        toast.setGravity(style.location)
        toast.setDuration(style.duration)
        toast.setAnim(style.anim)
        toast.x = style.x
        toast.y = style.y
        toast.setAlpha(style.alpha)
        toast.setListener(clickListener)
        return toast
    }


    override fun show(text: String) {
        _toast = WeakReference(createToast())
        toast?.setText(text)
        toast?.show()
    }

    override fun cancle() {
        toast?.cancel()
        _toast = null
        _style = null
    }


}