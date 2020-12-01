# Kotlin

## 基础

1. MainActivity
2. CodeView
3. User

1:04:00 之后

4. Utils
5. CacheUtils
6. BaseApplication
7. @file:JvmName("xxx")
8. HttpClient

---

### 改造

#### 4. Utils 和 CacheUtils

- 静态函数
    - 定义在文件中: 如 Utils
    - Object:

#### 6. BaseApplication(1:16 ~ 1:18)
- companion object: 实现单例
    - @JvmStatic: 在里面的方法上加上注解, 在 Java 类中用类名. 调用即可

---

7. @file:JvmName("xxx")
   -给 Kotlin 文件使用，在第一行。 注解之后，就直接可以使用 文件名. 调用了。

8. HttpClient(1:21:40 ~)

9. Lesson(1:37 ~)

10. LessonPresenter(1:38 ~)
    - const: 编译器常量

11. LessonAdapter(1:46 ~)
    - 自动转换成 Kotlin 代码，onCreate 方法去掉 private 修饰

12. internal(1:49:30 ) -> LessonAdapter
    - 模块内访问修饰符
    - updateAndNotify() 用 interval 修饰
    - onBind() 用 interval 修饰
    - LessonActivity showResult() 用 interval 修饰

13. BaseViewHolder(1:50:00 ~)
    - 用 abstract 修饰
    - getView() 方法用 protected open 修饰

14. abstract, open, override
    - 被这些关键字修饰的类才可以被继承或者被重写
---

## 进阶

1. 主构造器
    - 直接卸载类的声明上
    - LessonAdapter -> LessonViewHolder
    - Lesson(2:20)
    - User(3:20)
    - CodeView(4:20)

2. init { ... }
    - 初始化代码块
    - Lesson(2:55)
    - User(3:20)
    - CodeView(4:20)

3. 自动 setter getter
    - (5:40)
    - Kotlin 中，字段修饰符不是 private，就会对成员变量自动 setter getter
    - Lesson 去掉 getter setter 方法

4. 定义变量时，通过主构造器参数初始化，不需要 init { ... }
    - Lesson(6:40)

5. 在主构造器中初始化
    - Lesson(7:05)

6. 改造 User
    - (9:00)

7. data class
    - (10:00)
    - 帮助生成 copy() 函数
    - 复制对象时，用 copy() 函数很方便
    - app/com.example.app.entity 新建 Test.kt

8. ==
    - 相当于 java 中的 equals()
    - (12:30)

9. ===
    - 比较对象地址值

10. Kotlin 反编译为 Java
    - Tools -> Kotlin -> Show Kotlin Bytecode

11. 简写(17:50) -> LessonAdapter_LessonViewHolder_onBind()
    - 前
     ```
      var date = lesson.date
                  if (date == null) {
                      date = "日期待定"
                  }
     ```
    - 后
     ```
        var date = lesson.date?: "日期待定"
     ```

12. 简写(19:30)
    - MainActivity verify()
    - 前: user.username != null && user!!.username!!.length < 4
    - 后: user.username?.length?: 0 < 4
        - user.username?.length => 等价于 user.username != null
        - user.username?.length?: 0 => 等价于 user.username?.length 时 null ，没搞明白
    - 可读性不高

13. when() 有返回值(21:00 ~)
    - LessonAdapter -> LessonViewHolder_onBind()

14. 操作符(22:40 ~)
    - operator: 用这个修饰的方法，可以直接用操作符调用

15. 集合遍历简化操作(23:30 ~ 27:00)
    - LessonPresenter -> showPlayBack()
     ```
        lessons.forEach {
                    if (it.state === Lesson.State.PLAYBACK) {
                        playbackLessons.add(it)
                    }
                }
                activity!!.showResult(playbackLessons)

                //简化2
                val filter = lessons.filter { it.state === Lesson.State.PLAYBACK }
                activity!!.showResult(filter)
     ```

16. 循环(27:00 ~)
    - repeat(数字): Test.kt
        ```
            //输出 0-29
            repeat(30){
                    println(it)
                }
        ```
    - for(i in a..b){}
        ```
            //输出 0-30
            for (i in 0..30){
                println(i)
            }
        ```
    - util: 左闭右开
        ```
            //遍历数组
            val pi = arrayOf(3, 1, 4, 1, 5, 9, 2, 6)

            for (i in 0 until pi.size){
                println(pi[i])
            }
        ```
17. 函数嵌套(31:30 ~)
    - MainActivity : 可将 verify() 放在 login() 中定义
    - 每次被调用会产生一个额外对象
    - 不建议使用

18. (34:00 ~)
    - BaseApplication
    - 去掉 companion object{} 中的
        ```
            fun currentApplication():Context{
                return currentApplication
            }
        ```
    - 在成员变量后面增加
        ```
            private set
        ```
    - 成员变量定义
        ```
            //java 中调用1
            lateinit var currentApplication:Context
                    private set

            //java 中调用2
            @JvmStatic
            lateinit var currentApplication:Context
                    private set

            //java 中调用3
            @JvmStatic
            @get:JvmName("myApp")
            lateinit var currentApplication:Context
                    private set
        ```
        - Java 中调用
            1. BaseApplication.Companion.getCurrentApplication();
            2. BaseApplication.getCurrentApplication();
            3. BaseApplication.myApp();

19. 类型推断简化函数声明(36:40 ~)
    - CacheUitls
        1. 函数体就一行代码，简化
            ```
                //before
                fun get(key:String):String?{
                    return SP.getString(key,null)
                }

                //after
                fun get(key:String):String? = SP.getString(key,null)
                fun get(key:String) = SP.getString(key,null) //进一步简化
            ```
20. 函数参数默认值 简化函数(38:00 ~)
    - Utils
    - 简化
        ```
            //before
             fun toast(msg: String) {
                toast(msg, Toast.LENGTH_SHORT)
            }

            fun toast(msg: String, duration: Int) {
                Toast.makeText(BaseApplication.currentApplication, msg, duration)
            }

            //after
            fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
                Toast.makeText(BaseApplication.currentApplication, msg, duration)
            }
        ```
    - CodeView
    - 简化
        ```
            //before
            class CodeView constructor(context: Context,attrs:AttributeSet?) :AppCompatEditText(context,attrs){
                constructor(context:Context):this(context,null)
            }

            //after
            class CodeView constructor(context: Context,attrs:AttributeSet? = null) :AppCompatEditText(context,attrs){

            }
        ```
    - Java 中调用，简化的函数
        - 要在方法上面加上注解:  @JvmOverloads
        - 如果时主构造方法，在 constructor 前加上注解

21. 扩展函数(41:10 ~), 这个很牛叉

    - 扩展函数在编译的时候就已经确定了，在 Java 中相当于静态
    - Utils 的 dp2px() 方法
        ```
            //before
            fun dp2px(dp: Float): Float {
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
            }
            //CodeView init{ ... } 中调用
            paint.strokeWidth = dp2px(6f)

            //after
            fun Float.dp2px(): Float {
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
            }
            //CodeView init{ ... } 中调用
            paint.strokeWidth = 6f.dp2px()
        ```

    - 如果扩展函数名和原有的函数名相同，则调用的时原来自有的函数
    - 两个扩展函数有父子关系(44:00 ~)
        ```
            //在 MainActivity

        ```
22. 扩展属性(46:40 ~)
    - 给类扩展成员属性
    - MainActivity val ViewGroup.firstChildren
        ```
            (window.decorView as ViewGroup).firstChildren
        ```

23. inline(48:00 ~)
    - 内联函数: 放在函数声明 fun 之前
    - 调用内联函数时，直接把 内联函数的函数体内容复制过去
    - 节省调用栈
    - 增加编译时间
    - 当传入类型为函数类型时，用比较好

24. 函数类型(54:00 ~)
    - Test.kt -> main() -> 函数类型
    - 不太明白

25. 抽象属性
    - BaseView
        ```
            //before
            fun getPresenter():T

            //after
            val presenter:T
        ```
    - 回到 LessonActivity，实现接口的方法


26. 委托(1:04:40 ~) ->  by lazy. 不明白
    - LessonActivity
        ```
            //before
            private val lessonPresenter = LessonPresenter(this)
            override fun getPresenter(): LessonPresenter {
            return lessonPresenter

            //after
            override val presenter: LessonPresenter? by lazy {
                LessonPresenter(this)
            }
        ```
    - by lazy { ... } 效果
        - { ... } 内只会被执行一次
        - 在访问 presenter 变量的时候，才会执行 { ... }

---

下面时作用域操作符

27. apply{ ... } (1:14:00)
    - CodeView paint
        ```
            //before
            var paint:Paint = Paint()

             init {
                    ...
                    paint.isAntiAlias = true
                    paint.style = Paint.Style.STROKE
                    paint.color = getContext().getColor(R.color.colorAccent)
                    paint.strokeWidth = 6f.dp2px()
                    ...
                }

             //after
             var paint:Paint = Paint().apply {
                 isAntiAlias = true
                 style = Paint.Style.STROKE
                 color = getContext().getColor(R.color.colorAccent)
                 strokeWidth = 6f.dp2px()
             }

             init {
                ...
             }

        ```
28. let{ ... } (1:16:00 ~)
    - LessonAdapter 内部类 LessonViewHolder_onBind() 方法里
        - if (state != null) { ... } 之前, 替换:
            ```
                //before
                if (state != null) {
                    setText(R.id.tv_state, state.stateName()!!)
                    var colorRes = R.color.playback
                    colorRes = when (state) {
                        Lesson.State.PLAYBACK -> {

                            // 即使在 {} 中也是需要 break 的。
                            R.color.playback
                        }
                        Lesson.State.LIVE -> R.color.live
                        Lesson.State.WAIT -> R.color.wait
                    }
                    val backgroundColor = itemView.context.getColor(colorRes)
                    getView<View>(R.id.tv_state)?.setBackgroundColor(backgroundColor)
                }

                //after
                lesson.state?.let {
                    setText(R.id.tv_state, it?.stateName()!!)
                    var colorRes = R.color.playback
                    colorRes = when (it) {
                        Lesson.State.PLAYBACK -> {

                            // 即使在 {} 中也是需要 break 的。
                            R.color.playback
                        }
                        Lesson.State.LIVE -> R.color.live
                        Lesson.State.WAIT -> R.color.wait
                    }
                    val backgroundColor = itemView.context.getColor(colorRes)
                    getView<View>(R.id.tv_state)?.setBackgroundColor(backgroundColor)
                }
            ```
    - also, run 也能替换 lef, 替换后 { ... } 有少许不同

29. run (1:19:00 ~), 简化代码
    - LessonActivity
        ```
            //before
             val recyclerView = findViewById<RecyclerView>(R.id.list)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = lessonAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

            //after
            findViewById<RecyclerView>(R.id.list).run {
                layoutManager = LinearLayoutManager(this@LessonActivity)
                adapter = lessonAdapter
                addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
            }
        ```



