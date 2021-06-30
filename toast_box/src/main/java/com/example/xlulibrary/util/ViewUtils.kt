package com.example.xlulibrary.util

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.AdapterView

/**
 * @ClassName ViewUtils
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/30 11:37
 */
object ViewUtils {

    @Synchronized
    fun gcViews(view: View?) {
        if (view == null) {
            return
        }
        if (view.background != null) {
            view.background.callback = null
        }
        if (view is ViewGroup && view !is AdapterView<*>) {
            if (view is WebView) {
                val parent = view.getParent()
                if (parent != null) {
                    (parent as ViewGroup).removeView(view)
                }
                view.removeAllViews()
                view.destroy()
                return
            }
            for (i in 0 until view.childCount) {
                gcViews(view.getChildAt(i))
            }
            view.removeAllViews()
        }
    }
}