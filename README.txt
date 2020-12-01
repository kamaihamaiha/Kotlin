## Kotlin 基础

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
