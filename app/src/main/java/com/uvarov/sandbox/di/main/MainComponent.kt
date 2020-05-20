package com.uvarov.sandbox.di.main

import com.uvarov.sandbox.ui.main.MainFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(mainFragment: MainFragment)
}