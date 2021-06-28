package com.example.xlulibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.FloatRange
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.strategy.ToastStrategyImpl
import com.example.xlulibrary.strategy.ToastStrategy
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle

/**
 * @ClassName ToastBox
 * @Description 吐司工坊
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/17 16:40
 */
class ToastBox(){

    private val context:Context
        get() = ToastBoxRegister.getCurrentActivity()


    var mToastStyle : ToastStyle = NormalStyle()
    var mToastStrategy : ToastStrategy = ToastStrategyImpl(context)

    fun show(text: String?,duration:Long?=null):ToastBox  = apply{
        if (text.isNullOrEmpty()) {
            return@apply
        }
        duration?.let {
            mToastStyle.duration = it
        }
        mToastStrategy.setStyle(mToastStyle)
        mToastStrategy.show(text.toString())
    }

    fun show(res: Int?,duration:Long?=null){
        val text = res?.let { context.resources.getText(it) }
        if (text.isNullOrEmpty()) {
            return
        }
        show(text.toString(),duration)
    }

    fun setLocation(location: Location):ToastBox  = apply{
        mToastStyle.location = location
    }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float):ToastBox = apply{
        mToastStyle.alpha = alpha
    }

    fun setView(view: View):ToastBox = apply{
        mToastStyle.setView(view)
    }

    fun setView(id:Int):ToastBox = apply{
        val view = LayoutInflater.from(context).inflate(id,null)
        mToastStyle.setView(view)
    }

    fun setStyle(style: ToastStyle):ToastBox = apply{
        mToastStyle = style
    }

    fun setXY(x:Int?=null,y:Int?=null):ToastBox = apply{
        x?.let {
            mToastStyle.x = it
        }
        y?.let {
            mToastStyle.y = it
        }
    }

    fun setListener(listener:ToastClickItf):ToastBox = apply{
        mToastStrategy.setListener(listener)
    }

    fun dismiss(){
        mToastStrategy.cancle()
    }


    /**
     * TODO 设置通用显示样式
     * 默认提供白色和灰色两种样式，其他样式可通过自定义ToastStyle实现
     * @param textStyle
     * @return
     */
    fun setTextStyle(textStyle: TextStyle):ToastBox = apply{
        when(textStyle){
            TextStyle.Black -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_black
                    this.textStyle = R.style.NormalStyle_textAppreance_black
                }
            }
            TextStyle.White -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_white
                    this.textStyle = R.style.NormalStyle_textAppreance_white
                }
            }
        }
    }
}