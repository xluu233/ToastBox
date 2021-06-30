package com.example.xlulibrary.style

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.R

/**
 * @ClassName NormalStyle
 * @Description 自定义toast样式
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:12
 */
class NormalStyle : ToastStyle {

    override var location: Location = Location.BOTTOM
        set(value) {
            if (value!=Location.BOTTOM){
                x = 0
                y = 0
            }
            field = value
        }

    override var duration = 2500L

    override var alpha: Float = 1.0f

    override var x: Int = 0
        set(value) {
            xyChanged = true
            field = value
        }

    override var y: Int = 100
        set(value) {
            xyChanged = true
            field = value
        }

    override var backDrawable: Int = R.drawable.normal_shape_black

    override var textStyle: Int = R.style.NormalStyle_textAppreance_black

    override var anim: Int = R.style.MiuiToast

    var xyChanged = false

    override fun createView(context: Context) : View{
        return LayoutInflater.from(context).inflate(R.layout.toast_default, null)
    }

}