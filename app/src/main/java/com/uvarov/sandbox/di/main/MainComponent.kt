package com.uvarov.sandbox.di.main

import com.uvarov.sandbox.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}