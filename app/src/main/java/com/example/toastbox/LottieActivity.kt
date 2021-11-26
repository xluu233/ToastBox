package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.xlulibrary.Location
import com.example.xlulibrary.ToastBox

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        showAll()
    }

    private fun showAll() {
        ToastBox.setParams(layout = R.layout.toast_lottie_success, x = 0, y = 100, duration = 2500L).showToast("Success!")
        ToastBox.setParams(layout = R.layout.toast_lottie_error, x = 0, y = 500, duration = 3500L).showToast("Net Error!")
        ToastBox.setParams(layout = R.layout.toast_lottie_fail, x = 0, y = 900, duration = 4500L).showToast("Failed!")
    }


    private fun showSysToast(){
        //系统toast实现
        ToastBox.setParams(layout = R.layout.toast_lottie_success).showToast("Success!",system = true)
    }


    fun toast(view: View) {
        when(view.id){
            R.id.button13 -> {
                //info
                ToastBox.setParams(layout = R.layout.toast_lottie_info, location = Location.CENTER).showToast("Net Error!")
            }
            R.id.button14 -> {
                //net error
                ToastBox.setParams(layout = R.layout.toast_lottie_error, location = Location.TOP).showToast("Net Error!")
            }
            R.id.button15 -> {
                //success
                ToastBox.setParams(layout = R.layout.toast_lottie_success, location = Location.BOTTOM).showToast("Success!")
            }
            R.id.button16 -> {
                //fail
                ToastBox.setParams(layout = R.layout.toast_lottie_fail, x = 0, y = 900).showToast("Failed!")
            }
            R.id.button17 -> {
                showSysToast()
            }
        }
    }


}