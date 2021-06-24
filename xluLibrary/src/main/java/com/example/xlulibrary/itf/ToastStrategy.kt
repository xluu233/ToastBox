package com.example.xlulibrary.itf

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View

/**
 * @ClassName ToastD
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 15:45
 */
interface ToastStrategy {

    //fun init(app:Application)

    fun createToast(activity: Activity):Toast

    fun show(activity: Activity,text:String)

    fun cancle()

    fun setStyle(style:ToastStyle)


}