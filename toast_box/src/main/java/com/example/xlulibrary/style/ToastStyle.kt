package com.example.xlulibrary.style

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.example.xlulibrary.data.Location

/**
 * @ClassName ToastStyle
 * @Description 用来设置toast参数
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:07
 */
interface ToastStyle {

    /**
     * 创建视图
     */
    fun createView(context: Context):View

    /**
     * 位置
     */
    var location: Location

    /**
     * 显示时间
     */
    var duration:Long

    /**
     * 整体透明度
     */
    var alpha:Float

    /**
     * x，y坐标
     */
    var x:Int
    var y:Int

    /**
     * 背景样式
     */
    var backDrawable : Int?

    /**
     * 字体样式
     */
    var textStyle : Int?

    /**
     * 弹出动画
     */
    var animStyle:Int?


    /**
     * 默认图标
     * 以及图标padding
     */
    var iconDrawable:Int?
    var left:Int
    var top:Int
    var right:Int
    var bottom:Int

}