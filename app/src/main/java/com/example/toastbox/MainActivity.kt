package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.xlulibrary.ToastBox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastNormal(view: View) {
        when(view.id){
            R.id.button -> {
                ToastBox.show("ToastBox")
            }
            R.id.button2 -> {
                Toast.makeText(this,"shab",Toast.LENGTH_LONG).show()
            }
        }
    }

}