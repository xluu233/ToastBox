package com.example.xlulibrary.toast

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.StyleRes
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf

/**
 * @ClassName Toast
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:42
 */
interface xToast {

    var x:Int
    var y:Int
    var duration:Long


    /**
     * TODO 显示
     */
    fun show()

    /**
     * TODO 取消
     */
    fun cancel()

    /**
     * TODO 设置文本
     */
    fun setText(text:String)

    /**
     * TODO 设置布局
     */
    fun setView(view: View?)

    /**
     * TODO 获取布局
     */
    fun getView():View?

    /**
     * TODO 设置重心偏移
     */
    fun setGravity(location: Location)

    /**
     * TODO 获取显示重心
     */
    fun getGravity(): Int

    /**
     * TODO 设置动画样式
     */
    fun setAnimStyle(style:Int)
    fun getAnimStyle():Int

    /**
     * TODO 设置点击事件
     */
    fun setListener(clickItf: ToastClickItf?)
    fun getListener():ToastClickItf?

    /**
     * TODO 设置背景样式
     */
    fun setBackDrawable(drawable: Int)
    fun setBackDrawable(drawable: Drawable)
    fun getBackDrawable():Drawable?

    /**
     * TODO 设置字体样式
     */
    fun setTextStyle(@StyleRes style:Int)

    /**
     * TODO 设置默认显示图标
     */
    fun setIcon(drawable: Int?)

    /**
     * TODO 设置透明度
     */
    fun setAlpha(i:Float)
    //fun getAlpha():Float

    fun clear()

}