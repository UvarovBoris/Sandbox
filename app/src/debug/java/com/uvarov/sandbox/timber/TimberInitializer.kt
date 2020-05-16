package com.uvarov.sandbox.timber

import timber.log.Timber

object TimberInitializer {

    fun init() {
        Timber.plant(Timber.DebugTree())
    }
}