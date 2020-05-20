package com.uvarov.sandbox.di.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvarov.sandbox.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class AppModule {

    @AppScope
    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }
}