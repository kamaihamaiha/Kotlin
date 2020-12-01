package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication:Application() {

    companion object{
        @JvmStatic
        @get:JvmName("myApp")
        lateinit var currentApplication:Context
        private set

        /*@JvmStatic
        fun currentApplication():Context{
            return currentApplication
        }*/
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}