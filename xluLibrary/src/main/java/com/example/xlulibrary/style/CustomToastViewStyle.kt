package com.example.xlulibrary.style

import android.content.Context
import android.view.View
import com.example.xlulibrary.Location
import com.example.xlulibrary.itf.ToastStyle

/**
 * @ClassName CustomToastViewStyle
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 17:58
 */
class CustomToastViewStyle(layout:Int,style:ToastStyle<*>) : ToastStyle<View> {

    override var xOffset: Int = 0
    override var yOffset: Int = 0
    override var location:Location = Location.BOTTOM

    override var duration: Int = 0

    //val mStyle:ToastStyle<*> = style

    override fun createView(context: Context): View {
        TODO("Not yet implemented")
    }


}