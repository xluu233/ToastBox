package com.example.xlulibrary.toast

import android.app.Application
import android.graphics.drawable.Drawable
import android.view.View
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf

/**
 * @ClassName SystemToast
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/5 17:39
 */
class SystemToast(application: Application) : Toast {

    override var x: Int = 0
    override var y: Int = 0

    override fun show() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun setText(text: String) {
        TODO("Not yet implemented")
    }

    override fun setView(view: View?) {
        TODO("Not yet implemented")
    }

    override fun getView(): View? {
        TODO("Not yet implemented")
    }

    override fun setDuration(duration: Long) {
        TODO("Not yet implemented")
    }

    override fun getDuration(): Long {
        TODO("Not yet implemented")
    }

    override fun setGravity(location: Location) {
        TODO("Not yet implemented")
    }

    override fun getGravity(): Int {
        TODO("Not yet implemented")
    }

    override fun setAnim(anim: Int) {
        TODO("Not yet implemented")
    }

    override fun getAnim(): Int {
        TODO("Not yet implemented")
    }

    override fun setListener(clickItf: ToastClickItf?) {
        TODO("Not yet implemented")
    }

    override fun getListener(): ToastClickItf? {
        TODO("Not yet implemented")
    }

    override fun setBackDrawable(drawable: Int) {
        TODO("Not yet implemented")
    }

    override fun setBackDrawable(drawable: Drawable) {
        TODO("Not yet implemented")
    }

    override fun getBackDrawable(): Drawable? {
        TODO("Not yet implemented")
    }

    override fun setTextStyle(style: Int) {
        TODO("Not yet implemented")
    }

    override fun setIcon(drawable: Int?) {
        TODO("Not yet implemented")
    }

    override fun setAlpha(i: Float) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}