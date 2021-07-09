package com.example.xlulibrary.strategy

import android.content.Context
import android.view.View
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.style.ToastStyle

/**
 * @ClassName ToastD
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 15:45
 */
interface ToastStrategy {

    fun createToast(context:Context): xToast

    fun getIToast():xToast?

    fun show(context: Context,text:String)

    fun cancle()

    fun setStyle(style: ToastStyle)

    fun setListener(clickItf: ToastClickItf)

    fun setView(view: View)

    //fun setIcon(drawable:Int?,left:Int, top:Int, right:Int, bottom:Int)

}