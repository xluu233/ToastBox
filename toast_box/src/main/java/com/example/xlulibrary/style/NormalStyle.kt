package com.example.xlulibrary.style

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.xlulibrary.Location
import com.example.xlulibrary.R
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.ToastLifecycle.application
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


    private var customView:View ?= null

    override var view: View = getNormalLayout()
        get() = customView ?: field

    override var location: Location = ToastBox.location

    override var duration = ToastBox.duration

    override var alpha: Float = ToastBox.alpha

    override var x: Int = ToastBox.x

    override var y: Int = ToastBox.y

    override var backDrawable: Int ?= getDefaultBackDrawable()

    override var textTheme: Int ?= getDefaultTextAppearance()

    override var anim: Int ?= ToastBox.anim

    fun getNormalLayoutWithIcon():View{
        val layout = ConstraintLayout(application)
        layout.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            minHeight = 40.dp.toInt()
            elevation = 1.dp
            foregroundGravity = Gravity.CENTER
        }
        val image = ImageView(application)
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

        val textView = TextView(application)
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
        val layout = LinearLayout(application)
        layout.apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
            elevation = 1.dp
            gravity = Gravity.CENTER
            orientation = LinearLayout.HORIZONTAL
        }
        val textView = TextView(application)
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

}