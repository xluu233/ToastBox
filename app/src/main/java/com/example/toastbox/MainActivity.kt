package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.xlulibrary.Location
import com.example.xlulibrary.ToastBox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastNormal(view: View) {
        when(view.id){
            R.id.button -> {
                ToastBox.show("This is ToastBox")
            }
            R.id.button2 -> {
                ToastBox.setLocation(Location.CENTER).show("Center ToastBox")
            }
        }
    }

}