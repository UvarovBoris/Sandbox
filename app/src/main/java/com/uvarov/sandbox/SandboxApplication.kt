package com.uvarov.sandbox

import android.app.Application
import com.uvarov.sandbox.timber.TimberManager

class SandboxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TimberManager.init()
    }
}