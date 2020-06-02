package com.uvarov.sandbox

import android.content.Context
import okhttp3.OkHttpClient

object StethoInitializer {

    fun init(context: Context) {
        //NOP
    }

    fun initNetworkInterceptor(okHttpBuilder: OkHttpClient.Builder): OkHttpClient.Builder {
        return okHttpBuilder
    }
}