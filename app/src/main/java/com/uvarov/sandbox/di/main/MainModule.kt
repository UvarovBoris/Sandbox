package com.uvarov.sandbox.di.main

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MainModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(): ViewModel {
        return MainViewModel()
    }
}