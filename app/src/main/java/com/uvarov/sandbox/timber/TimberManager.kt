package com.uvarov.sandbox.timber

import com.uvarov.sandbox.BuildConfig
import timber.log.Timber

object TimberManager {

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }
}