package com.example.toastbox

import android.app.Application
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.TextStyle

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
            //text样式：白色和灰色
            textStyle = TextStyle.White
            //设置默认动画
            //animStyle = R.style.xxx
        }

    }


}