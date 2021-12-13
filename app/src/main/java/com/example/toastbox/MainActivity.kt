package com.example.toastbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.xlulibrary.Location
import com.example.xlulibrary.ToastTextStyle
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.ToastClickItf
import kotlin.concurrent.thread



class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastNormal(view: View) {
        when(view.id){
            R.id.button -> {
                //正常使用
                ToastBox.showToast("This is ToastBox")
            }
            R.id.lottie -> {
                //配合lottie使用
                val intent = Intent(this,LottieActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.button2 -> {
                //在不同的位置弹出
                ToastBox.setParams(location = Location.BOTTOM).showToast("Bottom ToastBox")
                ToastBox.setParams(location = Location.CENTER).showToast("Center ToastBox")
                ToastBox.setParams(location = Location.TOP).showToast("TOP ToastBox")
            }
            R.id.button3 -> {
                //修改透明度
                ToastBox.setParams(alpha = 0.5f).showToast("alpha toast")
            }
            R.id.button4 -> {
                //自定义布局，传入View或者layout
                ToastBox.setParams(layout = R.layout.toast_custom).showToast("自定义View")
            }
            R.id.button5 -> {
                //设置toast时间
                ToastBox.setParams(duration = 5000L).showToast("5000ms")
            }
            R.id.button6 -> {
                //设置xy坐标
                ToastBox.setParams(x=100,y=200).showToast("修改XY坐标")
            }
            R.id.button7 -> {
                //设置监听
                ToastBox.setParams(listener = object :ToastClickItf{
                    override fun setOnToastDismissed() {
                        Log.d(TAG,"toast dismissed")
                    }
                }).showToast("事件监听")
            }
            R.id.button8 -> {
                //预定义的三种样式
                ToastBox.setParams(defaultTextStyle = ToastTextStyle.Black).showToast("黑色Toast")
                ToastBox.setParams(defaultTextStyle = ToastTextStyle.White,x = 0, y = 300).showToast("白色Toast")
                ToastBox.setParams(defaultTextStyle = ToastTextStyle.GRAY,x = 0, y = 600).showToast("灰色Toast")
            }
            R.id.button9 -> {
                //子线程弹出
                thread {
                    ToastBox.showToast("${Thread.currentThread().name}")
                }
            }
            R.id.button10 -> {
                //测试activity切换情况下toast
                ToastBox.showToast(TAG)
                val intent = Intent(this,TestActivity::class.java)
                startActivity(intent)
            }
            R.id.button11 -> {
                //设置动画
                ToastBox.setParams(anim = R.style.ToastAnim_OPEN).showToast("动画切换1")
                ToastBox.setParams(anim = R.style.ToastAnim_ALPHA, x = 0, y = 300).showToast("动画切换2")
            }
        }
    }

}