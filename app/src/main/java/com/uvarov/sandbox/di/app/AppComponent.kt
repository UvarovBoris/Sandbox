package com.uvarov.sandbox.di.app

import com.uvarov.sandbox.di.breeds.BreedsComponent
import com.uvarov.sandbox.di.breeds.BreedsModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun createMainComponent(module: BreedsModule): BreedsComponent
}