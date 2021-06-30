package com.example.toastbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.data.TextStyle
import com.example.xlulibrary.itf.ToastClickItf
import kotlin.concurrent.thread

const val TAG = "ToastBox"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastNormal(view: View) {
        when(view.id){
            R.id.button -> {
                //正常使用
                ToastBox(this).show("This is ToastBox")
            }
            R.id.button2 -> {
                //在不同的位置弹出
                ToastBox(this).setLocation(Location.TOP).show("TOP ToastBox")
                ToastBox(this).setLocation(Location.CENTER).show("Center ToastBox")
                ToastBox(this).setLocation(Location.BOTTOM).show("Bottom ToastBox")
            }
            R.id.button3 -> {
                //修改透明度
                ToastBox(this).setLocation(Location.CENTER).setAlpha(0.5f).show("Center ToastBox")
            }
            R.id.button4 -> {
                //自定义布局，传入View或者layout
                ToastBox(this).setView(R.layout.custom_toast_common_1).show("This is Custom View")
            }
            R.id.button5 -> {
                //设置toast时间
                ToastBox(this).show("5000L",5000L)
            }
            R.id.button6 -> {
                //设置xy必须在设置location后面
                ToastBox(this).setLocation(Location.BOTTOM).setXY(100,200).show("Center ToastBox")
            }
            R.id.button7 -> {
                ToastBox(this).setListener(object : ToastClickItf{
                    override fun setOnTextClicked() {
                        Log.d(TAG,"text clicked")
                    }

                    override fun setOnToastDismissed() {
                        Log.d(TAG,"toast dismissed")
                    }
                }).show("哈啊啊啊啊啊哼哼",3000L)
            }
            R.id.button8 -> {
                ToastBox(this).setTextStyle(TextStyle.White).setXY(0,300).show("白色Toast")
                ToastBox(this).setTextStyle(TextStyle.Black).show("灰色Toast")
            }
            R.id.button9 -> {
                thread {
                    ToastBox(this).show("${Thread.currentThread().name}")
                }
            }
            R.id.button10 -> {
                ToastBox(this).show("Activity---1111")
                val intent = Intent(this,TestActivity2::class.java)
                startActivity(intent)
                //this.finish()
            }
            R.id.button11 -> {

            }
        }
    }

}