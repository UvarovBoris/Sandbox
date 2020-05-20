package com.uvarov.sandbox.di.app

import com.uvarov.sandbox.di.main.MainComponent
import com.uvarov.sandbox.di.main.MainModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun createMainComponent(module: MainModule): MainComponent
}