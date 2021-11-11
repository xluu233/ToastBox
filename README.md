# ToastBox

ToastBox是一个自定义吐司库，可以实现自定义View、显示时长、显示位置、各种自定义风格，使用便捷。有WindowsManger和系统Toast两种实现方式。

<div align=center>
<img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7253b6acce354403bbf61e73c46815ae~tplv-k3u1fbpfcp-watermark.image" width="400" height="XXX" />
</div>

更多实现效果请参考Demo


### 快速接入：

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	    implementation 'com.github.xluu233:ToastBox:0.6.1'
	}
	

### 快速使用：

**stetup1:** 在Application中初始化

```
ToastBoxRegister.init(this)
```

【可选】初始化时可以配置一些参数

```
ToastBoxRegister.init(this).apply {
    //text样式：白色和灰色
    textStyle = TextStyle.White
    //设置默认动画
    animStyle = R.style.xxx
}
```

**setup2**:简单调用

```
ToastBox().show("This is ToastBox")
```

### 详细设置


方法 | 作用
---|---
setLocation(Location.CENTER)| 修改弹出位置
setXY(100,200)|设置XY坐标
setAlpha(0.5f)| 修改透明度
setView(R.layout.toast_custom)| 自定义view
setListener()|设置监听
setTextStyle(TextStyle.GRAY)|设置通用样式
setAnim(R.style.MiuiToast)|设置弹出动画



### 更多演示

- 弹出系统toast

```
ToastBox.showSys("系统Toast实现",layout = R.layout.toast_lottie_fail,duration = Toast.LENGTH_LONG)
```
- 设置toast时间

```
ToastBox().show("5000L",duration = 5000)
```

如果想要实现更多自定义效果，建议传入自定义布局，自定义布局中以第一个`TextView`显示文本消息

```
//自定义布局，传入View或者layout
ToastBox().setView(R.layout.toast_custom).show("Warning",5000L)
```

或者自定义`ToastStyle`接口:

```
fun setStyle(style: ToastStyle)
```

接口信息如下：
```
interface ToastStyle {

    /**
     * 创建视图
     */
    fun createView():View

    /**
     * 位置
     */
    var location: Location

    /**
     * 显示时间
     */
    var duration:Long

    /**
     * 整体透明度
     */
    var alpha:Float

    /**
     * x，y坐标
     */
    var x:Int
    var y:Int

    /**
     * 背景样式
     */
    var backDrawable : Int?

    /**
     * 字体样式
     */
    var textStyle : Int?

    /**
     * 弹出动画
     */
    var animStyle:Int?


}
```

### 配合Lottie动画库可以做一些好看的toast

**success：**
<div align=center>
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7fd376adf1164baa81d13eebe5a92e94~tplv-k3u1fbpfcp-watermark.image" width="xxx" height="XXX" />
</div>

**fail:**
<div align=center>
<img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c181942878874969bfb318087386449f~tplv-k3u1fbpfcp-watermark.image" width="XXX" height="XXX" />
</div>

**网络错误：**
<div align=center>
<img src="https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1203ffe2ae564f4f9540b731b894103a~tplv-k3u1fbpfcp-watermark.image" width="XXX" height="XXX" />
</div>

**普通信息：**
<div align=center>
<img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/70788df056a54b378a9a914dcece1bba~tplv-k3u1fbpfcp-watermark.image" width="XXX" height="XXX" />
</div>

Lottie中有很多看好的动画，你也可以自己设计一个。

[Lottie动画](https://lottiefiles.com/featured)  
[Lottie开发文档](http://airbnb.io/lottie/#/android?id=loading-an-animation)
