package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.xlulibrary.ToastBox

class TestActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    override fun onResume() {
        super.onResume()

        ToastBox(this).show("Activity---2222",10000L)
    }
}