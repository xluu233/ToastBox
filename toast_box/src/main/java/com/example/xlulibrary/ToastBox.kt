package com.example.xlulibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.*
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.strategy.ToastStrategyImpl
import com.example.xlulibrary.strategy.ToastStrategy
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import java.lang.ref.WeakReference
import java.util.*

/**
 * @ClassName ToastBox
 * @Description 吐司工坊
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/17 16:40
 */
class ToastBox{

    private var _mToastStyle : ToastStyle ?= NormalStyle()
    private val mToastStyle get() = _mToastStyle!!

    private var _mToastStrategy : WeakReference<ToastStrategy> ?= WeakReference(ToastStrategyImpl())
    private val ToastStrategyImpl:ToastStrategy? get() = _mToastStrategy?.get()


    fun show(text: String?,duration:Long?=null){
        if (text.isNullOrEmpty()) {
            return
        }
        duration?.let {
            mToastStyle.duration = it
        }
        ToastStrategyImpl?.setStyle(mToastStyle)
        ToastStrategyImpl?.show(text.toString())
    }

    fun show(@StringRes res: Int?,duration:Long?=null):ToastBox  = apply{
        val text = res?.let { ToastBoxRegister.getActivity().resources.getText(it) }
        if (text.isNullOrEmpty()) {
            return@apply
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
        ToastStrategyImpl?.setView(view)
    }

    fun setView(@LayoutRes id:Int):ToastBox = apply{
        val view = LayoutInflater.from(ToastBoxRegister.getActivity()).inflate(id,null)
        ToastStrategyImpl?.setView(view)
    }

    fun setStyle(style: ToastStyle):ToastBox = apply{
        _mToastStyle = style
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
        ToastStrategyImpl?.setListener(listener)
    }

    fun dismiss(){
        ToastStrategyImpl?.cancle()
    }

    fun setIcon(@DrawableRes drawableRes: Int?, left: Int=0, top: Int=0, right: Int=0, bottom: Int=0):ToastBox = apply{
        mToastStyle.iconDrawable = drawableRes
        mToastStyle.left = left
        mToastStyle.right = right
        mToastStyle.top = top
        mToastStyle.bottom = bottom
    }

    /**
     * TODO 设置通用显示样式
     * 默认提供白色、黑色、灰色样式，其他样式可通过自定义ToastStyle实现
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
            TextStyle.GRAY -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_gray
                    this.textStyle = R.style.NormalStyle_textAppreance_gray
                }
            }
        }
    }

    fun setAnim(@StyleRes anim: Int):ToastBox = apply{
        mToastStyle.animStyle = anim
    }


}