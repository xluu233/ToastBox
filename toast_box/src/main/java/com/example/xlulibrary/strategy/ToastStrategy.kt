package com.example.xlulibrary.strategy

import android.content.Context
import android.view.View
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.toast.Toast
import com.example.xlulibrary.style.ToastStyle

/**
 * @ClassName ToastD
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 15:45
 */
interface ToastStrategy {

    //fun init(app:Application)

    fun createToast(): Toast

    fun show(text:String)

    fun cancle()

    fun setStyle(style: ToastStyle)

    fun setListener(clickItf: ToastClickItf)

    var view:View?
}