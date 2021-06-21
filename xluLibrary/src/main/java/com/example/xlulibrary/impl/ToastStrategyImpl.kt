package com.example.xlulibrary.impl

import android.app.Application
import android.content.Context
import android.view.View
import com.example.xlulibrary.ActivityStack
import com.example.xlulibrary.itf.Toast
import com.example.xlulibrary.itf.ToastStrategy
import com.example.xlulibrary.itf.ToastStyle
import com.example.xlulibrary.style.NormalStyle

/**
 * @ClassName ToastStrategyImpl
 * @Description toast显示策略
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:40
 */
class ToastStrategyImpl : ToastStrategy {

    private var application:Application ?= null

    private var toast:Toast ?= null

    private var activityStack:ActivityStack ?= null

    private var toastStyle:ToastStyle = NormalStyle()


    override fun init(app: Application){
        application = app
        activityStack = ActivityStack.register(app)
    }

    override fun setStyle(style: ToastStyle) {
        toastStyle = style
    }

    override fun createToast(): Toast {
        val activity = activityStack?.foregroundActivity!!
        val toast = BaseToast(activity)
        toast.setView(toastStyle.createView(activity))
        toast.setGravity(toastStyle.location, toastStyle.xOffset, toastStyle.yOffset)
        toast.setDuration(toastStyle.duration)
        return toast
    }


    override fun show(text: String) {
        //toast?.cancel()
        toast = createToast()
        toast?.setText(text)
        toast?.show()
    }

    override fun cancle() {
        toast?.cancel()
    }


}