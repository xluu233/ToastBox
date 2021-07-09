# ToastBOX


```
ToastBox是一个自定义吐司库，可以实现多种自定义toast
```
![demo](https://github.com/xluu233/ToastBox/blob/master/img/box%20(1).jpg)

### 具体功能

自定义View、显示时长、显示位置、各种自定义风格


<table>
    <tr>
        <td ><center><img src="https://github.com/xluu233/ToastBox/blob/master/img/box%20(2).jp">nav1</center></td>
        <td ><center><img src="https://github.com/xluu233/ToastBox/blob/master/img/box%20(2).jp">nav2</center></td>
        <td ><center><img src="https://github.com/xluu233/ToastBox/blob/master/img/box%20(2).jp">nav3</center></td>
    </tr>
</table>


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
	    implementation 'com.github.xluu233:ToastBox:0.3'
	}

[![](https://jitpack.io/v/xluu233/ToastBox.svg)](https://jitpack.io/#xluu233/ToastBox)

### 使用：

stetup1: 在Application中初始化

```
ToastBoxRegister.init(this)

```

setup2：简单调用

```
ToastBox(this).show("This is ToastBox")
```

### 更多演示请参考demo


```
//正常使用
ToastBox(this).show("This is ToastBox")


//在不同的位置弹出
ToastBox(this).setLocation(Location.TOP).show("TOP ToastBox")
ToastBox(this).setLocation(Location.CENTER).show("Center ToastBox")
ToastBox(this).setLocation(Location.BOTTOM).show("Bottom ToastBox")


//修改透明度
ToastBox(this).setLocation(Location.CENTER).setAlpha(0.5f).show("Center ToastBox")


//自定义布局，传入View或者layout
ToastBox(this).setView(R.layout.custom_toast_common_1).show("This is Custom View",5000L)


//设置toast时间
ToastBox(this).show("5000L",duration = 5000)


//设置xy必须在设置location后面
ToastBox(this).setLocation(Location.BOTTOM).setXY(100,200).show("Center ToastBox")


//设置监听
ToastBox(this).setListener(object : ToastClickItf{
    override fun setOnToastDismissed() {
        xLog.d(TAG,"toast dismissed")
    }
}).show("哈啊啊啊啊啊哼哼",3000L)



//不同风格的toast
ToastBox(this).setTextStyle(TextStyle.GRAY).show("灰色Toast")
ToastBox(this).setTextStyle(TextStyle.White).setXY(0,300).show("白色Toast")
ToastBox(this).setTextStyle(TextStyle.Black).setXY(0,600).show("黑色Toast")


//设置动画
ToastBox(this).setAnim(R.style.MiuiToast).show("切换弹出动画")

//自定义图标
ToastBox(this).setIcon(R.drawable.icon).show("This is ToastBox")
```
