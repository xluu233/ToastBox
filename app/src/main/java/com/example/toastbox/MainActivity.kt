package com.example.toastbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.ToastBox.Companion.toast
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.dp
import com.example.xlulibrary.util.xLog
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
                //ToastBox().show("This is ToastBox")

                toast("jhaha")
            }
            R.id.lottie -> {
                //配合lottie使用
                val intent = Intent(this,LottieActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                //在不同的位置弹出
                ToastBox().setLocation(Location.TOP).show("TOP ToastBox")
                ToastBox().setLocation(Location.CENTER).show("Center ToastBox")
                ToastBox().setLocation(Location.BOTTOM).show("Bottom ToastBox")
            }
            R.id.button3 -> {
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
                ToastBox().setListener(object : ToastClickItf{
                    override fun setOnToastDismissed() {
                        xLog.d(TAG,"toast dismissed")
                    }
                }).show("哈啊啊啊啊啊哼哼",3000L)
            }
            R.id.button8 -> {
                //设置弹出XY位置
                ToastBox().setTextStyle(TextStyle.GRAY).show("灰色Toast")
                ToastBox().setTextStyle(TextStyle.White).setXY(0,300).show("白色Toast")
                ToastBox().setTextStyle(TextStyle.Black).setXY(0,600).show("黑色Toast")
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
            }
        }
    }

}