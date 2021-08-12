package com.example.xlulibrary.util

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.xlulibrary.ToastBoxRegister

private var oldMsg: String? = null
private var time: Long = 0


fun finalToast(content: String, duration: Int = Toast.LENGTH_SHORT,context: Context) = ToastBoxRegister.getActivity().runOnUiThread{
    if (!TextUtils.isEmpty(content)){
        if (content != oldMsg) {
            // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(context, content, duration).show()
            time = System.currentTimeMillis()
        } else {
            // 显示内容一样时，只有间隔时间大于3秒时才显示
            if (System.currentTimeMillis() - time > 3000) {
                Toast.makeText(context, content, duration).show()
                time = System.currentTimeMillis()
            }
        }
        oldMsg = content
    }
}

fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content))return
    finalToast(content, duration,ToastBoxRegister.getActivity())
}

fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    finalToast(ToastBoxRegister.getActivity().getString(id), duration,ToastBoxRegister.getActivity())
}

fun longToast(content: String, duration: Int = Toast.LENGTH_LONG) {
    if (TextUtils.isEmpty(content))return
    finalToast(content, duration,ToastBoxRegister.getActivity())
}

fun longToast(@StringRes id: Int, duration: Int = Toast.LENGTH_LONG) {
    finalToast(ToastBoxRegister.getActivity().getString(id), duration,ToastBoxRegister.getActivity())
}


