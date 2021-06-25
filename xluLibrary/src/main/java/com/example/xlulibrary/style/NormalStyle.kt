package com.example.xlulibrary.style

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.R

/**
 * @ClassName NormalStyle
 * @Description TODO
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

    var xyChanged = false
    var mView:View?=null

    override fun createView(context: Context) : View{
        mView?.let {
            return mView!!
        }
        val toastView = LayoutInflater.from(context).inflate(R.layout.toast_default, null)
        toastView.alpha = alpha
        return toastView
    }

    override fun setView(view: View) {
        mView = view
    }


}