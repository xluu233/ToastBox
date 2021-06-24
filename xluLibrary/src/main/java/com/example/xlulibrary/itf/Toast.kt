package com.example.xlulibrary.itf

import android.view.View
import com.example.xlulibrary.Location

/**
 * @ClassName Toast
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:42
 */
interface Toast {

    /**
     * 显示
     */
    fun show()

    /**
     * 取消
     */
    fun cancel()

    /**
     * 设置文本
     */
    fun setText(text:String)

    /**
     * 设置布局
     */
    fun setView(view: View)

    /**
     * 获取布局
     */
    fun getView():View?

    /**
     * 设置显示时长
     */
    fun setDuration(duration: Long)

    /**
     * 获取显示时长
     */
    fun getDuration(): Long

    /**
     * 设置重心偏移
     */
    fun setGravity(location: Location)

    /**
     * 获取显示重心
     */
    fun getGravity(): Int

    /**
     * 设置屏幕间距
     */
    fun setMargin(horizontalMargin: Float, verticalMargin: Float)

    /**
     * 设置水平间距
     */
    fun getHorizontalMargin(): Float

    /**
     * 设置垂直间距
     */
    fun getVerticalMargin(): Float

    /**
     * 设置style样式
     */
    fun setStyle(style:Int)


//    /**
//     * 设置透明度
//     */
//    fun setAlpha(i:Float)
//
//    /**
//     * 获取透明值
//     */
//    fun getAlpha():Float

}