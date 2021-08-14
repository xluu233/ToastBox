# ToastBOX


> ToastBox是一个自定义吐司库，可以实现自定义View、显示时长、显示位置、各种自定义风格。有WindowsManger和系统Toast两种实现方式，配合Lottie动画库可以做一些好看的toast

### 展示

<div align=center>
<img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7253b6acce354403bbf61e73c46815ae~tplv-k3u1fbpfcp-watermark.image" width="400" height="XXX" />
</div>

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
	    implementation 'com.github.xluu233:ToastBox:0.4'
	}
	

### 使用：

**stetup1:** 在Application中初始化

```
        ToastBoxRegister.init(this)
```

初始化时可以配置一些参数

```
        ToastBoxRegister.init(this).apply {
            //text样式：白色和灰色
            textStyle = TextStyle.White
            //可以设置默认显示图标
            defaultIcon = R.drawable.ic_launcher_background
            //设置默认动画
            animStyle = R.style.xxx
        }
```

**setup2**:简单调用

```
ToastBox(this).show("This is ToastBox")
```

### 更多参数设置


```
//正常使用
ToastBox().show("This is ToastBox")

//在不同的位置弹出
ToastBox().setLocation(Location.TOP).show("TOP ToastBox")
ToastBox().setLocation(Location.CENTER).show("Center ToastBox")
ToastBox().setLocation(Location.BOTTOM).show("Bottom ToastBox")

//修改透明度
ToastBox().setLocation(Location.CENTER).setAlpha(0.5f).show("Center ToastBox")

//自定义布局，传入View或者layout
ToastBox().setView(R.layout.custom_toast_common_1).show("This is Custom View",5000L)

//设置toast时间
ToastBox().show("5000L",duration = 5000)

//设置xy必须在设置location后面
ToastBox().setLocation(Location.BOTTOM).setXY(100,200).show("Center ToastBox")

//设置监听
ToastBox().setListener(object : ToastClickItf{
    override fun setOnToastDismissed() {
        xLog.d(TAG,"toast dismissed")
    }
}).show("哈啊啊啊啊啊哼哼",3000L)

//不同风格的toast
ToastBox().setTextStyle(TextStyle.GRAY).show("灰色Toast")
ToastBox().setTextStyle(TextStyle.White).setXY(0,300).show("白色Toast")
ToastBox().setTextStyle(TextStyle.Black).setXY(0,600).show("黑色Toast")

//设置动画
ToastBox().setAnim(R.style.MiuiToast).show("切换弹出动画")

//自定义图标
ToastBox().setIcon(R.drawable.icon).show("This is ToastBox")
```

---
卑微Androider求一个Star*


**相关文章**:  
[写一个MVVM快速开发框架（一）基础类封装 ](https://juejin.cn/post/6989918599007698957) 

[写一个MVVM快速开发框架（二）组件化改造](https://juejin.cn/post/6995082240287850527)
