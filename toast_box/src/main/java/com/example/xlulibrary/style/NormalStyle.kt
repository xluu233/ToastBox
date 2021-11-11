package com.example.xlulibrary.style

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.util.Utils.dp
import com.example.xlulibrary.util.Utils.getDefaultBackDrawable
import com.example.xlulibrary.util.Utils.getDefaultTextAppearance

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

    override var backDrawable: Int ?= getDefaultBackDrawable()

    override var textStyle: Int ?= getDefaultTextAppearance()

    override var animStyle: Int ?= ToastBoxRegister.animStyle

    var xyChanged = false

    fun getNormalLayout2():View{
        val layout = ConstraintLayout(ToastBoxRegister.application)
        layout.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            minHeight = 40.dp.toInt()
            elevation = 1.dp
            foregroundGravity = Gravity.CENTER
        }
        val image = ImageView(ToastBoxRegister.application)
        val imageParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(40.dp.toInt(),40.dp.toInt())
        imageParams.apply {
            startToStart = 0
            bottomToBottom = 0
            topToTop = 0

            marginStart = 0.dp.toInt()
            topMargin = 0.dp.toInt()
            bottomMargin = 0.dp.toInt()
        }
        image.apply {
            id = R.id.normal_icon
            layoutParams = imageParams
            visibility = View.GONE
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
        layout.addView(image)

        val textView = TextView(ToastBoxRegister.application)
        val textParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        textParams.apply {
            startToStart = R.id.normal_icon
            bottomToBottom = 0
            topToTop = 0
            endToEnd = 0

            marginStart = 15.dp.toInt()
            topMargin = 5.dp.toInt()
            bottomMargin = 5.dp.toInt()
            marginEnd = 15.dp.toInt()
        }
        textView.apply {
            id = R.id.normal_text
            layoutParams = textParams
            gravity = Gravity.CENTER
        }
        layout.addView(textView)

        return layout
    }

    private fun getNormalLayout():View{
        val layout = LinearLayout(ToastBoxRegister.application)
        layout.apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
            elevation = 1.dp
            gravity = Gravity.CENTER
            orientation = LinearLayout.HORIZONTAL
        }

        val textView = TextView(ToastBoxRegister.application)
        val textParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        textParams.apply {
            setMargins(15.dp.toInt(),5.dp.toInt(),15.dp.toInt(),5.dp.toInt())
        }
        textView.apply {
            id = R.id.normal_text
            layoutParams = textParams
            gravity = Gravity.CENTER
            minHeight = 30.dp.toInt()
        }
        layout.addView(textView)

        return layout
    }

    override fun createView() : View{
        return getNormalLayout()
    }

}