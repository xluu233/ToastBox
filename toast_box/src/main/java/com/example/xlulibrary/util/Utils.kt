package com.example.xlulibrary.util

import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.data.TextStyle

internal object Utils {
    fun getLocalGravity(location: Location): Int {
        return when(location){
            Location.TOP -> {
                Gravity.TOP
            }
            Location.CENTER -> {
                Gravity.CENTER
            }
            Location.BOTTOM -> {
                Gravity.BOTTOM
            }
        }
    }

    val Int.dp get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), ToastBoxRegister.application.resources.displayMetrics)


    /**
     * 自定义View布局中必须包含一个TextView，返回第一个TextView
     */
    fun findMessageView(view: View?): TextView? {
        if (view == null) return null
        if (view is TextView) {
            return view
        }
        if (view is ViewGroup){
            for (i in 0 until view.childCount){
                return when(val childView = view.getChildAt(i)){
                    is ViewGroup -> {
                        findMessageView(childView)
                    }
                    is TextView -> {
                        childView
                    }
                    else -> {
                        continue
                    }
                }
            }
        }
        return null
    }

    /**
     * 自定义View布局中必须包含一个ImageView，返回第一个ImageView
     */
    fun findImageView(view: View?): ImageView? {
        if (view == null) return null
        if (view is ImageView) return view

        if (view is ViewGroup){
            for (i in 0 until view.childCount){
                return when(val childView = view.getChildAt(i)){
                    is ViewGroup -> {
                        findImageView(childView)
                    }
                    is ImageView -> {
                        childView
                    }
                    else -> {
                        continue
                    }
                }
            }
        }
        return null
        //throw IllegalArgumentException("You ViewGroup must include a ImageView")
    }


    fun getDefaultBackDrawable():Int{
        var drawablw:Int = com.example.xlulibrary.R.drawable.normal_shape_gray
        ToastBoxRegister.textStyle?.let {
            drawablw = when(it){
                TextStyle.Black -> {
                    com.example.xlulibrary.R.drawable.normal_shape_black
                }
                TextStyle.White -> {
                    com.example.xlulibrary.R.drawable.normal_shape_white
                }
                TextStyle.GRAY -> {
                    com.example.xlulibrary.R.drawable.normal_shape_gray
                }
            }
        }
        return drawablw
    }

    fun getDefaultTextAppearance():Int{
        var drawablw:Int = com.example.xlulibrary.R.style.NormalStyle_textAppreance_gray
        ToastBoxRegister.textStyle?.let {
            drawablw = when(it){
                TextStyle.Black -> {
                    com.example.xlulibrary.R.style.NormalStyle_textAppreance_black
                }
                TextStyle.White -> {
                    com.example.xlulibrary.R.style.NormalStyle_textAppreance_white
                }
                TextStyle.GRAY -> {
                    com.example.xlulibrary.R.style.NormalStyle_textAppreance_gray
                }
            }
        }
        return drawablw
    }

    fun getLayoutView(@LayoutRes layout:Int):View{
        return LayoutInflater.from(ToastBoxRegister.application).inflate(layout, null)
    }
}
