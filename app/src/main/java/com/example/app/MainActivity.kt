package com.example.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val usernameKey: String = "username"
    val passwordKey: String = "password"


    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    fun Activity.log(log:String){
        Log.d("Activity", log)
    }

    fun Context.log(log:String){
        Log.d("Context", log)
    }

    val ViewGroup.firstChildren:View
        get() = getChildAt(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // (window.decorView as ViewGroup).firstChildren

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login: Button = findViewById(R.id.btn_login)
        val img_code: CodeView = findViewById(R.id.code_view)

        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v is CodeView){
            v.updateCode()
        } else if (v is Button){
            login()
        }
    }

    fun login() {
        val username: String = et_username.text.toString()
        val password: String = et_password.text.toString()
        val code: String = et_code.text.toString()

        val user: User = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey,username)
            CacheUtils.save(passwordKey,password)
            startActivity(Intent(this,LessonActivity::class.java))
        }
    }

    fun verify(user: User): Boolean {
        if (user.username != null && user!!.username!!.length < 4) {
            Utils.toast("用户名不合法")
            return false
        }
        if (user.password != null && user!!.password!!.length < 4) {
            Utils.toast("密码不合法")
            return false
        }
        return true
    }
}