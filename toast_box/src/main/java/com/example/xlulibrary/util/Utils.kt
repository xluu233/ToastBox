package com.example.xlulibrary.util

import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.xlulibrary.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.ToastLifecycle.application
import com.example.xlulibrary.ToastTextStyle

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

    val Int.dp get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), application.resources.displayMetrics)


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
        return when(ToastBox.toastTextStyle){
            ToastTextStyle.Black -> {
                R.drawable.normal_shape_black
            }
            ToastTextStyle.White -> {
                R.drawable.normal_shape_white
            }
            ToastTextStyle.GRAY -> {
                R.drawable.normal_shape_gray
            }
        }
    }

    fun getDefaultTextAppearance():Int{
        return when(ToastBox.toastTextStyle){
            ToastTextStyle.Black -> {
                R.style.NormalStyle_textAppreance_black
            }
            ToastTextStyle.White -> {
                R.style.NormalStyle_textAppreance_white
            }
            ToastTextStyle.GRAY -> {
                R.style.NormalStyle_textAppreance_gray
            }
        }
    }

    fun getLayoutView(@LayoutRes layout:Int?):View?{
        return if (layout==null) null else LayoutInflater.from(application).inflate(layout, null)
    }
}
