package com.example.xlulibrary.util


import android.R
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.data.TextStyle

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


/**
 * 智能获取用于显示消息的 TextView
 */
fun findMessageView(view: View): TextView {
    if (view is TextView) {
        if (view.getId() == View.NO_ID) {
            view.setId(R.id.message)
        } else require(view.getId() == R.id.message) {
            // 必须将 TextView 的 id 值设置成 android.R.id.message
            "You must set the ID value of TextView to android.R.id.message"
        }
        return view
    }
    if (view.findViewById<View>(R.id.message) is TextView) {
        return view.findViewById<View>(R.id.message) as TextView
    }
    throw IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message")
}

/**
 * 自定义View布局中必须包含一个ImageView
 */
fun findImageView(view: View?): ImageView? {
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