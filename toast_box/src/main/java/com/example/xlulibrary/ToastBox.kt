package com.example.xlulibrary

import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.*
import com.example.xlulibrary.ToastLifecycle.application
import com.example.xlulibrary.ToastLifecycle.getActivity
import com.example.xlulibrary.strategy.ToastStrategy
import com.example.xlulibrary.strategy.ToastStrategyImpl
import com.example.xlulibrary.style.NormalStyle
import com.example.xlulibrary.style.ToastStyle
import com.example.xlulibrary.toast.xToast
import com.example.xlulibrary.util.xLog
import util.Utils
import java.lang.ref.WeakReference
import java.util.concurrent.LinkedBlockingQueue


object ToastBox {

    private val TAG = "ToastBoxRegister"

    private var _mToastStyle : WeakReference<ToastStyle> ?= null
    private val mToastStyle: ToastStyle? get() = _mToastStyle?.get()

    private var _mToastStrategy : WeakReference<ToastStrategy> ?= null
    private val mToastStrategy: ToastStrategy? get() = _mToastStrategy?.get()

    private var _toast:WeakReference<Toast> ?= null
    private val toast get() = _toast?.get()



    /**
     * toast弹出位置
     */
    var location : Location = Location.BOTTOM

    /**
     * 设置默认的toast字体和背景样式
     * （这个是预先定义好的）
     */
    var toastTextStyle :ToastTextStyle = ToastTextStyle.White

    /**
     * 字体主题样式
     */
    var textTheme:Int = Utils.getDefaultTextAppearance()

    /**
     * toast背景样式
     */
    var backDrawable:Int = Utils.getDefaultBackDrawable()

    /**
     * 设置toast默认弹出动画
     */
    var anim :Int = R.style.ToastAnim_OPEN

    /**
     * 设置toast同时最多弹出的数量
     */
    var maxToastSize:Int = 3

    /**
     * toast透明度
     */
    var alpha:Float = 1.0f

    /**
     * 弹出时间
     */
    var duration:Long = 2500L

    /**
     * 弹出的x、y坐标
     */
    var x:Int = 0
    var y:Int = 100

    /**
     * 重新初始化默认toast style和strategy
     */
    private fun initToastStrategy(){
        _mToastStyle = WeakReference(NormalStyle())
        _mToastStrategy = WeakReference(ToastStrategyImpl())
    }

    /**
     * 初始化操作
     */
    fun init(
        x:Int = this.x,
        y:Int = this.y,
        duration: Long = this.duration,
        defaultTextStyle: ToastTextStyle = this.toastTextStyle,
        location: Location = this.location,
        maxToastSize:Int = this.maxToastSize,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float = this.alpha,
        @StyleRes anim:Int = this.anim,
        @DrawableRes backDrawable: Int = this.backDrawable,
        @StyleRes textTheme :Int = this.textTheme
    ){
        this.x = x
        this.y = y
        this.location = location
        this.anim = anim
        this.maxToastSize = maxToastSize
        this.alpha = alpha
        this.duration = duration
        this.toastTextStyle = defaultTextStyle
        this.backDrawable = backDrawable
        this.textTheme = textTheme
    }

    /*是否设置了自定义参数*/
    @Volatile
    private var hasSetToastStyle: Boolean = false

    /**
     * 弹出toast之前设置参数，不会影响默认配置，优先级高于默认配置
     */
    fun setParams(x:Int = this.x,
                  y:Int = this.y,
                  duration: Long = this.duration,
                  @FloatRange(from = 0.0, to = 1.0) alpha: Float = this.alpha,
                  @StyleRes anim:Int = this.anim,
                  defaultTextStyle: ToastTextStyle = this.toastTextStyle,
                  location: Location = this.location,
                  @DrawableRes backDrawable: Int ?= null,
                  @StyleRes textTheme :Int ?= null,
                  @LayoutRes layout: Int ?= null,
                  view: View ?= null,
                  listener:ToastClickItf ?=null
    ):ToastBox = apply{
        initToastStrategy()
        mToastStyle?.let {
            it.x = x
            it.y = y
            it.alpha = alpha
            it.duration = duration
            it.anim = anim
            it.location = location
            backDrawable?.let { backDrawable->
                it.backDrawable = backDrawable
            }
            textTheme?.let { textTheme->
                it.textTheme = textTheme
            }
            initDefaultToastStyle(defaultTextStyle)
        }
        layout?.let {
            mToastStrategy?.setView(Utils.getLayoutView(it)!!)
        }
        view?.let {
            mToastStrategy?.setView(it)
        }
        listener?.let {
            mToastStrategy?.setListener(it)
        }
        mToastStyle?.let {
            mToastStrategy?.setStyle(it)
        }
        hasSetToastStyle = true
    }


    private fun initDefaultToastStyle(defaultTextStyle: ToastTextStyle) {
        mToastStyle?.let {
            when(defaultTextStyle){
                ToastTextStyle.Black -> {
                    mToastStyle.apply {
                        it.backDrawable = R.drawable.normal_shape_black
                        it.textTheme = R.style.NormalStyle_textAppreance_black
                    }
                }
                ToastTextStyle.White -> {
                    mToastStyle.apply {
                        it.backDrawable = R.drawable.normal_shape_white
                        it.textTheme = R.style.NormalStyle_textAppreance_white
                    }
                }
                ToastTextStyle.GRAY -> {
                    mToastStyle.apply {
                        it.backDrawable = R.drawable.normal_shape_gray
                        it.textTheme = R.style.NormalStyle_textAppreance_gray
                    }
                }
            }
        }
    }


    fun showToast(@StringRes res:Int,system: Boolean = false){
        val content = application.resources.getString(res)
        if (content.isEmpty()) {
            return
        }
        showToast(content,system)
    }

    fun showToast(content: Any?, system: Boolean = false) = getActivity()?.runOnUiThread{
        val str = content?.toString()
        if (str.isNullOrEmpty()) {
            return@runOnUiThread
        }
        if (system){
            //系统toast
            if (hasSetToastStyle){
                val duration = if (mToastStyle?.duration ?: 2500L >= 2500L) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
                toast(text = str,duration = duration,view = mToastStrategy?.getView(),  location = mToastStyle?.location ?: Location.BOTTOM)
            }else{
                //默认参数
                val duration = if (this.duration > 2500L) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
                toast(text = str,duration = duration)
            }
        }else{
            //ActivityToast
            if (hasSetToastStyle){
                mToastStrategy?.show(str)
            }else{
                initToastStrategy()
                mToastStyle?.let {
                    mToastStrategy?.setStyle(it)
                }
                mToastStrategy?.show(str)
            }
        }
    }


    /**
     * Android11之后不在支持系统toast设置View
     */
    private fun toast(text:String, duration: Int = Toast.LENGTH_SHORT, view: View?= null, @LayoutRes layout:Int ?= null, location: Location = Location.BOTTOM){
        val looper = getActivity()?.mainLooper
        val handler = looper?.let {
            Handler(it)
        }
        val context = application
        handler?.post {
            toast?.cancel()
            _toast = null
            if (view==null && layout==null){
                _toast = WeakReference(Toast.makeText(context,text,duration))
            }else{
                _toast = WeakReference(Toast(context))
                val _view:View ?= view ?: Utils.getLayoutView(layout)
                toast?.view = _view

                val textView = Utils.findMessageView(_view)
                textView?.text = text

                when(location){
                    Location.BOTTOM -> {
                        toast?.setGravity(Gravity.BOTTOM, 0, 100)
                    }
                    Location.CENTER -> {
                        toast?.setGravity(Gravity.CENTER, 0, 0)
                    }
                    Location.TOP -> {
                        toast?.setGravity(Gravity.TOP, 0, -100)
                    }
                }
                toast?.duration = duration
            }
            toast?.show()
        }
    }


    init {
        initToastStrategy()
    }

}
