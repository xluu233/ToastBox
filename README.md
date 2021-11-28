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
	    implementation 'com.github.xluu233:ToastBox:0.7.0'
	}
	

### 快速使用：

**stetup1:** 初始化（可省略）

```
        //init可以省略，也可以配置一些全局默认参数
        ToastBox.init(
            duration = 3500L,
            alpha = 1.0f,
            anim = R.style.MiuiToast,
            location = Location.CENTER,
            x = 0,y = 100,
        )

```


**setup2**:简单调用

```
ToastBox.showToast("This is ToastBox")
```

### 具体使用

**使用setParams()设置自定义参数：**

- 自定义布局，传入view或者layout
> ToastBox.setParams(layout = R.layout.toast_custom).showToast("自定义View")


- 在不同的位置弹出,设置`location`参数，预定义了三种方式：
> ToastBox.setParams(location = Location.BOTTOM).showToast("Bottom ToastBox")
> ToastBox.setParams(location = Location.CENTER).showToast("Center ToastBox")
> ToastBox.setParams(location = Location.TOP).showToast("TOP ToastBox")

- 自定义弹出位置XY坐标
> ToastBox.setParams(x=100,y=200).showToast("修改XY坐标")


- 修改透明度:`alpha`
> ToastBox.setParams(alpha = 0.5f).showToast("alpha toast")


- 设置toast时间：`duration`
> ToastBox.setParams(duration = 5000L).showToast("5000ms")


- toast消失监听:`ToastClickItf`
```
ToastBox.setParams(listener = object :ToastClickItf{
      override fun setOnToastDismissed() {
            Log.d(TAG,"toast dismissed")
      }
}).showToast("事件监听")
```

- toast风格样式,预定义的三种样式: `ToastTextStyle.Black/White/Gray`
> ToastBox.setParams(defaultTextStyle = ToastTextStyle.Black).showToast("黑色Toast")
> ToastBox.setParams(defaultTextStyle = ToastTextStyle.White,x = 0, y = 300).showToast("白色Toast")
> ToastBox.setParams(defaultTextStyle = ToastTextStyle.GRAY,x = 0, y = 600).showToast("灰色Toast")

- 设置动画,预定义了三种动画：`ToastAnim_OPEN`，`ToastAnim_ALPHA`，`ToastAnim_MIUI`
> ToastBox.setParams(anim = R.style.ToastAnim_OPEN).showToast("动画切换1")
> ToastBox.setParams(anim = R.style.ToastAnim_ALPHA, x = 0, y = 300).showToast("动画切换2")


### setParams()详细参数

参数 | 详情 |  作用
---|---|---
duration | 默认 duration:Long = 2500L | 弹出时间 
location | 参考：Location.BOTTOM、 Location.CENTER、Location.TOP | 修改弹出位置
x,y | 示例：：setParams(x=100,y=200) | 设置XY坐标
alpha |  @FloatRange(from = 0.0, to = 1.0),示例：setParams(alpha = 0.5f) | 修改透明度
view、layout| 示例：setParams(view = XX,layout = R.layout.xx) | 自定义view
anim | 参考：`R.style.ToastAnim_OPEN`，`R.style.ToastAnim_ALPHA`，`R.style.ToastAnim_MIUI`| 设置动画
defaultTextStyle | 预定义的三种风格：`ToastTextStyle.Black/White/Gray` 黑色、白色、灰色 | 设置Toast风格
textTheme |  @StyleRes，示例：setParams(textTheme=R.style.xx) |  自定义字体主题
backDrawable | @DrawableRes，示例：setParams(backDrawable = R.drawable.xx) | 设置背景样式
listener | ToastClickItf | 设置toast消失事件监听


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
