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
            }
            R.id.button2 -> {
                //在不同的位置弹出
                ToastBox.setParams(location = Location.BOTTOM).showToast("Bottom ToastBox")
                ToastBox.setParams(location = Location.CENTER).showToast("Center ToastBox")
                ToastBox.setParams(location = Location.TOP).showToast("TOP ToastBox")
            }
            /*R.id.button3 -> {
                //修改透明度
                ToastBox().setLocation(Location.CENTER).setAlpha(0.5f).show("Center ToastBox")
            }
            R.id.button4 -> {
                //自定义布局，传入View或者layout
                ToastBox().setView(R.layout.toast_custom).show("Warning",5000L)
            }
            R.id.button5 -> {
                //设置toast时间
                ToastBox().show("5000L",duration = 5000)
            }
            R.id.button6 -> {
                //设置xy必须在设置location后面
                ToastBox().setLocation(Location.BOTTOM).setXY(100,200).show("Center ToastBox")
            }
            R.id.button7 -> {
                //设置监听
                ToastBox().setListener(object : ToastClickItf {
                    override fun setOnToastDismissed() {
                        Log.d(TAG,"toast dismissed")
                    }
                }).show("哈啊啊啊啊啊哼哼",3000L)
            }
            R.id.button8 -> {
                //设置弹出XY位置
                ToastBox().setTextStyle(ToastTextStyle.GRAY).show("灰色Toast")
                ToastBox().setTextStyle(ToastTextStyle.White).setXY(0,300).show("白色Toast")
                ToastBox().setTextStyle(ToastTextStyle.Black).setXY(0,600).show("黑色Toast")
            }
            R.id.button9 -> {
                //子线程弹出
                thread {
                    ToastBox().show("${Thread.currentThread().name}")
                }
            }
            R.id.button10 -> {
                //测试activity切换情况下toast
                ToastBox().show("MainActivity.class")
                val intent = Intent(this,TestActivity::class.java)
                startActivity(intent)
            }
            R.id.button11 -> {
                //设置动画
                ToastBox().setAnim(R.style.MiuiToast).show("切换弹出动画")
            }*/
        }
    }

}