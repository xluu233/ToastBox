package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.example.xlulibrary.ToastBox

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
    }

    fun toast(view: View) {
        when(view.id){
            R.id.button13 -> {
                //info
                ToastBox().setView(R.layout.toast_lottie_error).show("Error")
            }
            R.id.button14 -> {
                //net error
                ToastBox().setView(R.layout.toast_lottie_error).show("Error")
            }
            R.id.button15 -> {
                //success

            }
            R.id.button16 -> {
                //fail


            }
        }
    }


}