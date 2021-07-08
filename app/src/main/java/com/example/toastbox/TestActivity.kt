package com.example.toastbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toastbox.databinding.ActivityTestBinding
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.ToastType

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }


    override fun onResume() {
        super.onResume()
        if (ToastBoxRegister.toastType != ToastType.SystemToast){
            ToastBox(this).show("这是Activity-2弹出的toast",10000L)
        }
    }


    private fun initView() {
        //val fragmentContainerView = binding.fragment

        supportFragmentManager.beginTransaction().add(R.id.fragment,TestFragment.newInstance()).commit()
    }

}