package com.example.core.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

object Utils {

    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
    }

    fun toast(msg: String) {
        toast(msg, Toast.LENGTH_SHORT)
    }

    fun toast(msg: String, duration: Int) {
        Toast.makeText(BaseApplication.currentApplication(), msg, duration)
    }


}