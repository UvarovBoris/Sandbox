package com.uvarov.sandbox

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.uvarov.sandbox.di.app.AppComponent
import com.uvarov.sandbox.di.app.DaggerAppComponent
import com.uvarov.sandbox.timber.TimberInitializer

class SandboxApplication : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        TimberInitializer.init()
        Stetho.initializeWithDefaults(this);
    }
}