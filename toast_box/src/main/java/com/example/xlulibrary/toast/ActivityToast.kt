package com.example.xlulibrary.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.example.xlulibrary.ToastBoxRegister
import com.example.xlulibrary.data.Location
import com.example.xlulibrary.itf.ToastClickItf
import com.example.xlulibrary.util.ViewUtils
import com.example.xlulibrary.util.findMessageView
import com.example.xlulibrary.util.getLocaGravity
import java.lang.ref.WeakReference

/**
 * @ClassName ToastImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/6/18 17:10
 */
class ActivityToast(private val context: Context) : Toast {

    override var x: Int = 0
    override var y: Int = 0

    /** Toast 显示重心  */
    var mGravity = 0

    /** Toast 显示时长  */
    var mDuration = 0L

    /*动画*/
    var anim:Int ?= null

    private var mView:View ?= null
    private var mMessageView : TextView ?= null

    /*事件监听*/
    private var clickListener:ToastClickItf ?= null


    private val toast by lazy {
        if (context is Activity){
            WindowsMangerToast(context as Activity,this)
        }else{
            WindowsMangerToast(ToastBoxRegister.getActivity(),this)
        }
    }

    override fun show() {
        toast.show()
    }

    override fun cancel() {
        toast.cancle()
    }

    override fun setText(text: String) {
        mMessageView?.text = text
    }

    override fun setView(view: View?) {
        mView = view
        if (mView==null){
            mMessageView = null
            return
        }
        mMessageView = findMessageView(mView!!)
    }

    override fun getView(): View? {
        return mView
    }

    override fun setDuration(duration: Long) {
        mDuration = duration
    }

    override fun getDuration(): Long {
        return mDuration
    }

    override fun setGravity(location: Location) {
        mGravity = getLocaGravity(location)
    }

    override fun getGravity(): Int {
        return mGravity
    }


    override fun setAnim(anim: Int) {
        this.anim = anim
    }

    override fun getAnim(): Int {
        return anim!!
    }

    override fun setListener(clickItf: ToastClickItf?) {
        this.clickListener = clickItf
    }

    override fun getListener(): ToastClickItf? {
        return clickListener
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun setBackDrawable(drawable: Int) {
        mView?.background = context.getDrawable(drawable)
    }

    override fun setBackDrawable(drawable: Drawable) {
        mView?.background = drawable
    }

    override fun getBackDrawable(): Drawable? {
        return mView?.background
    }

    override fun setTextStyle(style: Int) {
        mMessageView?.setTextAppearance(style)
    }

    override fun setAlpha(i: Float) {
        mView?.alpha = i
    }

    override fun clear() {
        ViewUtils.gcViews(mView)
        mMessageView = null
        mView = null
    }


}
