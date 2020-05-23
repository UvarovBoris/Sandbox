package com.uvarov.sandbox.di.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.api.DogService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider


@Module
class AppModule {

    @AppScope
    @Provides
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }

    @AppScope
    @Provides
    fun provideDogService(): DogService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DogService::class.java)
    }
}