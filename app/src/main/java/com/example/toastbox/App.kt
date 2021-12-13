package com.example.toastbox

import android.app.Application
import com.example.xlulibrary.Location
import com.example.xlulibrary.ToastBox

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/21 9:58
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()


        //init可以省略，也可以配置一些全局默认参数
        ToastBox.init(
            duration = 3500L,
            alpha = 0.8f,
            anim = R.style.ToastAnim_MIUI,
            location = Location.BOTTOM,
            x = 0,y = 100,
        )

    }



}