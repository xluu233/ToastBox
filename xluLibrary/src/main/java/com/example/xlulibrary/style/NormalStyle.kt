package com.example.xlulibrary.style

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import com.example.xlulibrary.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.itf.ToastStyle

/**
 * @ClassName NormalStyle
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:12
 */
class NormalStyle : ToastStyle {

    override var location:Location = Location.BOTTOM
    override var duration = 2500L


    override fun createView(activity: Activity) : View{
        val toastView = activity.layoutInflater.inflate(R.layout.toast_default, null)
        return toastView
    }


}