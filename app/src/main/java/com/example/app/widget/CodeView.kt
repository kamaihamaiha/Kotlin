package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatEditText
import com.example.app.R
import com.example.core.utils.Utils
import com.example.core.utils.dp2px
import kotlin.random.Random

class CodeView @JvmOverloads constructor(context: Context, attrs:AttributeSet? = null) :AppCompatEditText(context,attrs)  {

    var paint:Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = getContext().getColor(R.color.colorAccent)
        strokeWidth = 6f.dp2px()
    }
    var codeList = arrayOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
    )
    //constructor(context:Context):this(context,null)

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        updateCode()
    }



    fun updateCode(){
        val random:Int = Random.nextInt(codeList.size)
        val code:String = codeList[random]
        setText(code)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f,height.toFloat(),width.toFloat() ,0f,paint)
        super.onDraw(canvas)
    }
}