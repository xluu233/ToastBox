package com.example.xlulibrary.itf

import android.app.Activity
import android.content.Context
import android.view.View
import com.example.xlulibrary.Location

/**
 * @ClassName ToastStyle
 * @Description 用来设置toast参数
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:07
 */
interface ToastStyle {

    fun createView(activity: Activity):View

    var location:Location

    var duration:Long

    var alpha:Float

}