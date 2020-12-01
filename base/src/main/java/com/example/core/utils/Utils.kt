package com.example.core.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics


/*fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}*/

fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}

/* fun toast(msg: String) {
     toast(msg, Toast.LENGTH_SHORT)
 }*/

object Utils {
    @JvmOverloads
    fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(BaseApplication.currentApplication, msg, duration)
    }


}