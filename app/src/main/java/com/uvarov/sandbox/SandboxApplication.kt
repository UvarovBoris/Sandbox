package com.uvarov.sandbox

import android.app.Application
import com.uvarov.sandbox.timber.TimberInitializer

class SandboxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TimberInitializer.init()
    }
}