package com.uvarov.sandbox

import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient

object StethoInitializer {

    fun init(context: Context) {
        Stetho.initializeWithDefaults(context)
    }

    fun initNetworkInterceptor(okHttpBuilder: OkHttpClient.Builder): OkHttpClient.Builder {
        return okHttpBuilder.addNetworkInterceptor(StethoInterceptor())
    }
}