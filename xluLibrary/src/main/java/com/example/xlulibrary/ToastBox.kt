package com.example.xlulibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.FloatRange
import com.example.xlulibrary.impl.ToastStrategyImpl
import com.example.xlulibrary.itf.ToastStrategy
import com.example.xlulibrary.itf.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import java.lang.ref.WeakReference

/**
 * @ClassName ToastBox
 * @Description 吐司工坊
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/17 16:40
 */
class ToastBox(context: Context){


    var mToastStyle : ToastStyle = NormalStyle()
    var mToastStrategy : ToastStrategy = ToastStrategyImpl()

    private var app:Application ?= null

    private lateinit var activityStack : WeakReference<ActivityStack>
    //private lateinit var currentActivity:WeakReference<Activity>


    fun init(application: Application, toastStyle: ToastStyle? = null){
        app = application
        activityStack = WeakReference<ActivityStack>(ActivityStack.register(application))
        //currentActivity = WeakReference(activityStack.get()?.foregroundActivity)

        toastStyle?.let {
            mToastStyle = it
        }
        mToastStrategy.setStyle(mToastStyle)
    }


    fun show(text: String?){
        if (text.isNullOrEmpty()) {
            return
        }
        mToastStrategy.show(activityStack.get()?.foregroundActivity!!,text)
    }

    fun setLocation(location: Location):ToastBox{
        mToastStyle.location = location
        return this
    }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float):ToastBox{
        mToastStyle.alpha = alpha
        return this
    }

    fun setView(view: View):ToastBox{

        return this
    }

    fun setView(id:Int):ToastBox{
        val view = LayoutInflater.from(activityStack.get()?.foregroundActivity!!).inflate(id,null)
        return this
    }

}