package com.example.core

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder(itemView: View) : ViewHolder(itemView) {

    private val viewHashMap: MutableMap<Int, View> = HashMap()

   protected open fun <T : View?> getView(id: Int): T? {
        var view: View? = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T
    }

   protected open fun setText(id:Int,text:String?){
        getView<TextView>(id)?.text = text
    }
}