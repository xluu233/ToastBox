package com.example.xlulibrary.toast

import android.graphics.Paint
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf

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

    var x:Int
    var y:Int

    /**
     * 设置动画样式
     */
    fun setAnim(anim:Int)

    fun getAnim():Int

    fun setListener(clickItf: ToastClickItf?)

    fun getListener():ToastClickItf?

}