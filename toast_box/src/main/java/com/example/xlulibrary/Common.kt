package com.example.xlulibrary


/*toast位置*/
enum class Location {
    TOP,
    CENTER,
    BOTTOM
}

/*toast风格*/
enum class TextStyle {
    Black,
    White,
    GRAY
}

/*事件监听*/
interface ToastClickItf {
    fun setOnToastDismissed()
}