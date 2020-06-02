package com.uvarov.sandbox

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient

object FlipperInitializer {

    private val networkFlipperPlugin: NetworkFlipperPlugin = NetworkFlipperPlugin()

    fun init(context: Context) {
        if (FlipperUtils.shouldEnableFlipper(context)) {
            SoLoader.init(context, false)
            AndroidFlipperClient.getInstance(context).apply {
                addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
                addPlugin(networkFlipperPlugin)
                addPlugin(DatabasesFlipperPlugin(context))
                addPlugin(SharedPreferencesFlipperPlugin(context))
                start()
            }
        }
    }

    fun initNetworkInterceptor(okHttpBuilder: OkHttpClient.Builder): OkHttpClient.Builder {
        return okHttpBuilder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
    }
}