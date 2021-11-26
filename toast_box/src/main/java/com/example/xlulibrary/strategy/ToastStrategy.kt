package com.example.xlulibrary.strategy

import android.view.View
import com.example.xlulibrary.ToastClickItf
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.style.ToastStyle

/**
 * @ClassName ToastD
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 15:45
 */
interface ToastStrategy {

    fun createToast(): xToast?

    fun getIToast():xToast?

    fun setStyle(style: ToastStyle)

    fun setListener(clickItf: ToastClickItf)

    fun setView(view: View)

    fun show(text:String)

    fun cancle()

}