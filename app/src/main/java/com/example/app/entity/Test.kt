package com.example.app.entity

val user = User("1", "2", "3")

val userCopy = user.copy()

class View {
    /**
     * 点击监听
     * View: 输入
     * Unit: 输出. 因为 onClick() 没有返回值
     */
    fun setOnClickListener(listener: (View) -> Unit) {}
}

fun onClick() {
    print("click...")
}

fun main() {

    var view:View = View()
    //函数类型（当作参数传递）
    view.setOnClickListener { ::onClick }

    println(user == userCopy)

    repeat(30) {
        println(it)
    }

    print("---\n")
    for (i in 0..30) {
        println(i)
    }

    print("---\n")
    //遍历数组
    val pi = arrayOf(3, 1, 4, 1, 5, 9, 2, 6)

    for (i in 0 until pi.size) {
        println(pi[i])
    }
}