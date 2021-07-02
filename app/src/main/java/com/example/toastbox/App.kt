package com.example.toastbox

import android.app.Application
import android.widget.Toast
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.data.ToastType

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/21 9:58
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastBoxRegister.init(this).apply {
            //toast类型：系统toast，windowsManger实现自定义toast
            toastType = ToastType.WindowsToast
            //text样式：白色和灰色
            textStyle = TextStyle.GRAY
        }
    }


}