package com.example.xlulibrary.style

import android.content.Context
import android.view.View
import com.example.xlulibrary.data.Location

/**
 * @ClassName ToastStyle
 * @Description 用来设置toast参数
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:07
 */
interface ToastStyle {

    fun createView(context: Context):View

    fun setView(view: View)

    var location: Location

    var duration:Long

    var alpha:Float

    var x:Int
    var y:Int

}