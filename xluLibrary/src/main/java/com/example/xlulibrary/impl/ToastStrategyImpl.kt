package com.example.xlulibrary.impl

import android.app.Activity
import android.app.Application
import android.view.View
import com.example.xlulibrary.ActivityStack
import com.example.xlulibrary.R
import com.example.xlulibrary.itf.Toast
import com.example.xlulibrary.itf.ToastStrategy
import com.example.xlulibrary.itf.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import java.lang.ref.WeakReference

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl : ToastStrategy {


    private var toast:WeakReference<Toast> ?= null

    private var defaultStyle:ToastStyle = NormalStyle()



    override fun setStyle(style: ToastStyle) {
        defaultStyle = style
    }

    override fun createToast(activity: Activity): Toast {
        val toast = BaseToast(activity)
        toast.setView(defaultStyle.createView(activity))
        toast.setGravity(defaultStyle.location)
        toast.setDuration(defaultStyle.duration)
        return toast
    }

    fun setView(view: View){

    }

    override fun show(activity: Activity,text: String) {
        toast = WeakReference(createToast(activity))
        toast?.get()?.setText(text)
        toast?.get()?.show()
    }

    override fun cancle() {
        toast?.get()?.cancel()
    }


}