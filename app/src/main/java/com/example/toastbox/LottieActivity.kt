package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.data.Location

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        showAll()
    }

    private fun showAll() {
        ToastBox().setView(R.layout.toast_lottie_success).setXY(0,100).show("Success!")
        ToastBox().setView(R.layout.toast_lottie_error).setXY(0,500).show("Net Error!")
        ToastBox().setView(R.layout.toast_lottie_fail).setXY(0,900).show("Failed!")
    }


    private fun showSysToast(){
        ToastBox.showSys("系统Toast实现",layout = R.layout.toast_lottie_fail,duration = Toast.LENGTH_LONG)
        //ToastBox.showSys("Sys Toast Success",layout = R.layout.toast_lottie_success,location = Location.CENTER )
        //ToastBox.showSys("Sys Toast Net",layout = R.layout.toast_lottie_error,location = Location.TOP )
    }


    fun toast(view: View) {
        when(view.id){
            R.id.button13 -> {
                //info
                ToastBox().setView(R.layout.toast_lottie_info).show("Show Message~")
            }
            R.id.button14 -> {
                //net error
                ToastBox().setView(R.layout.toast_lottie_error).show("Net Error!")
            }
            R.id.button15 -> {
                //success
                ToastBox().setView(R.layout.toast_lottie_success).show("Success!")
            }
            R.id.button16 -> {
                //fail
                ToastBox().setView(R.layout.toast_lottie_fail).show("Failed!")
            }
            R.id.button17 -> {
                showSysToast()
            }
        }
    }


}