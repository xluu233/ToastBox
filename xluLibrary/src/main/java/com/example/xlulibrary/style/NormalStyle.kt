package com.example.xlulibrary.style

import android.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import com.example.xlulibrary.Location
import com.example.xlulibrary.getLocaGravity
import com.example.xlulibrary.itf.ToastStyle

/**
 * @ClassName NormalStyle
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 16:12
 */
class NormalStyle : ToastStyle<TextView> {

    override var xOffset: Int = 0
    override var yOffset: Int = 0
    override var location:Location = Location.BOTTOM

    override var duration: Int = 2500

    companion object{
        var textSize:Float = 16f
        var textColor:Int = -0x11000001
        var maxLine:Int = 5
    }

    override fun createView(context: Context): TextView {
        val textView = TextView(context)
        textView.id = R.id.message
        textView.gravity = getLocaGravity(location)
        textView.setTextColor(textColor)
        textView.textSize = textSize

        val horizontalPadding: Int = getHorizontalPadding(context)
        val verticalPadding: Int = getVerticalPadding(context)

        // 适配布局反方向特性
        textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        // 设置背景
        val background: Drawable = getBackgroundDrawable(context)
        textView.background = background

        // 设置 Z 轴阴影
        textView.z = getTranslationZ(context)

        // 设置最大显示行数
        textView.maxLines = maxLine
        return textView
    }


    private fun getHorizontalPadding(context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, context.resources.displayMetrics).toInt()
    }

    private fun getVerticalPadding(context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, context.resources.displayMetrics).toInt()
    }

    private fun getBackgroundDrawable(context: Context): Drawable {
        val drawable = GradientDrawable()
        // 设置颜色
        drawable.setColor(-0x78000000)
        // 设置圆角
        drawable.cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, context.resources.displayMetrics)
        return drawable
    }

    private fun getTranslationZ(context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, context.resources.displayMetrics)
    }




}