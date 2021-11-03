package com.example.xlulibrary

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.*
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.strategy.ToastStrategyImpl
import com.example.xlulibrary.strategy.ToastStrategy
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.style.NormalStyle
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLayoutView
import java.lang.ref.WeakReference
import java.util.*

/**
 * @ClassName ToastBox
 * @Description 吐司工坊
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/17 16:40
 */
class ToastBox{

    private var _mToastStyle : WeakReference<ToastStyle> = WeakReference(NormalStyle())
    private val mToastStyle:ToastStyle get() = _mToastStyle.get()!!

    private var _mToastStrategy : WeakReference<ToastStrategy> ?= WeakReference(ToastStrategyImpl())
    private val toastStrategyImpl:ToastStrategy? get() = _mToastStrategy?.get()

    companion object{

        private var _toast:WeakReference<Toast> ?= null
        private val toast get() = _toast?.get()

        private var oldMsg: String? = null
        private var time: Long = 0

        /*系统toast实现*/
        fun showSys(@StringRes res: Int,duration: Int = Toast.LENGTH_LONG,view: View ?= null,@LayoutRes layout:Int ?= null,location: Location = Location.BOTTOM){
            val content = ToastBoxRegister.application.resources.getString(res)
            showSys(content,duration,view, layout, location)
        }

        fun showSys(text: Any?,duration: Int = Toast.LENGTH_LONG,view: View ?= null,@LayoutRes layout:Int ?= null,location: Location = Location.BOTTOM){
            val content = text?.toString()
            if (content.isNullOrEmpty()) {
                return
            }
            toast?.cancel()
            _toast = null
            _toast = WeakReference(Toast(ToastBoxRegister.application))

            view?.let {
                toast?.view = it
            }
            layout?.let {
                toast?.view = getLayoutView(layout)
            }
            val textView = findMessageView(toast?.view)
            textView?.text = content

            when(location){
                Location.BOTTOM -> {
                    toast?.setGravity(Gravity.BOTTOM, 0, 100)
                }
                Location.CENTER -> {
                    toast?.setGravity(Gravity.CENTER, 0, 0)
                }
                Location.TOP -> {
                    toast?.setGravity(Gravity.TOP, 0, -100)
                }
            }
            toast?.duration = duration
            toast?.show()
        }

    }

    /*----------主要调用方法------------*/
    fun show(text: Any?,duration:Long?=null){
        val content = text?.toString()
        if (content.isNullOrEmpty()) {
            return
        }
        duration?.let {
            mToastStyle.duration = it
        }
        toastStrategyImpl?.setStyle(mToastStyle)
        toastStrategyImpl?.show(content)
    }

    fun show(@StringRes res: Int,duration:Long?=null):ToastBox  = apply{
        val content = ToastBoxRegister.application.resources.getString(res)
        if (content.isEmpty()) {
            return@apply
        }
        show(content)
    }

    /*---------参数设置--------------*/
    fun setLocation(location: Location):ToastBox  = apply{
        mToastStyle.location = location
    }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float):ToastBox = apply{
        mToastStyle.alpha = alpha
    }

    fun setView(view: View):ToastBox = apply{
        toastStrategyImpl?.setView(view)
    }

    fun setView(@LayoutRes id:Int):ToastBox = apply{
        val view = getLayoutView(id)
        toastStrategyImpl?.setView(view)
    }

    fun setStyle(style: ToastStyle):ToastBox = apply{
        _mToastStyle = WeakReference(style)
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
        toastStrategyImpl?.setListener(listener)
    }

    fun dismiss(){
        toastStrategyImpl?.cancle()
    }

    /**
     * 设置通用显示样式, 默认提供白色、黑色、灰色样式，其他样式可通过自定义ToastStyle实现
     * @param textStyle
     * @return
     */
    fun setTextStyle(style: TextStyle):ToastBox = apply{
        when(style){
            TextStyle.Black -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_black
                    textStyle = R.style.NormalStyle_textAppreance_black
                }
            }
            TextStyle.White -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_white
                    textStyle = R.style.NormalStyle_textAppreance_white
                }
            }
            TextStyle.GRAY -> {
                mToastStyle.apply {
                    backDrawable = R.drawable.normal_shape_gray
                    textStyle = R.style.NormalStyle_textAppreance_gray
                }
            }
        }
    }

    /**
     * 自定义字体style
     */
    fun setTextStyle(@StyleRes style:Int):ToastBox = apply {
        mToastStyle.apply {
            textStyle = style
        }
    }

    /**
     * 自定义背景样式
     */
    fun setBackground(@DrawableRes drawable:Int):ToastBox = apply {
        mToastStyle.apply {
            backDrawable = drawable
        }
    }

    /**
     * 设置动画
     * @param anim
     */
    fun setAnim(@StyleRes anim: Int):ToastBox = apply{
        mToastStyle.animStyle = anim
    }


}