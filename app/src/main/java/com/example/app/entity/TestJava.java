package com.example.app.entity;

import com.example.core.BaseApplication;
import com.example.core.utils.Utils;

public class TestJava {

    public static void main(String[] args) {

        //调用1
//        BaseApplication.Companion.getCurrentApplication();
        //调用2
//        BaseApplication.getCurrentApplication();
        //调用3
        BaseApplication.myApp();

        Utils.INSTANCE.toast("");

    }
}
