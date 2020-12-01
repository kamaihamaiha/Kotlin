package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {

    val gson: Gson = Gson()

    private fun <T> convert(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String, type: Type, entityCallback: EntityCallback<T>) {
        /**
         *  final Request request = new Request.Builder()
        .url("https://api.hencoder.com/" + path)
        .build();
        final Call call = INSTANCE.newCall(request);
         */

        val request: Request = Request.Builder()
                .url("https://api.hencoder.com/ + ${path}")
                .build()
        val call: Call = this.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..300 -> entityCallback.onSuccess(convert<Any>(response.body().toString(), type) as T)
                    in 400..500 -> entityCallback.onFailure("客户端错误")
                    in 500..600 -> entityCallback.onFailure("服务器错误")
                    else -> entityCallback.onFailure("未知错误")
                }
            }
        })
    }
}