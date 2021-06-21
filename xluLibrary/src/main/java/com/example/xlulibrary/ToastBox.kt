package com.example.xlulibrary

import android.app.Application
import android.content.Context
import com.example.xlulibrary.impl.ToastStrategyImpl
import com.example.xlulibrary.itf.ToastStrategy
import com.example.xlulibrary.itf.ToastStyle
import com.example.xlulibrary.style.NormalStyle

/**
 * @ClassName ToastBox
 * @Description 吐司工坊
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/17 16:40
 */
object ToastBox{

    var mToastStyle : ToastStyle = NormalStyle()
    var mToastStrategy : ToastStrategy = ToastStrategyImpl()

    fun init(application: Application,toastStyle: ToastStyle?=null){
        mToastStrategy.init(application)
        if (toastStyle!=null){
            mToastStyle = toastStyle
        }
        mToastStrategy.setStyle(mToastStyle)
    }

    fun setDefaultStyle(toastStyle: ToastStyle){
        mToastStyle = toastStyle
        mToastStrategy.setStyle(mToastStyle)
    }

    fun setStrategy(strategy: ToastStrategy){
        mToastStrategy = strategy
    }

    fun show(text: String?){
        if (text == null || text.isEmpty()) {
            return
        }
        mToastStrategy.show(text)
    }



}