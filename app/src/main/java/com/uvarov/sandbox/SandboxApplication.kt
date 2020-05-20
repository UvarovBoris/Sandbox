package com.uvarov.sandbox

import android.app.Application
import com.facebook.stetho.Stetho
import com.uvarov.sandbox.di.app.AppComponent
import com.uvarov.sandbox.di.app.DaggerAppComponent
import com.uvarov.sandbox.timber.TimberInitializer

class SandboxApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        TimberInitializer.init()
        Stetho.initializeWithDefaults(this);
    }
}