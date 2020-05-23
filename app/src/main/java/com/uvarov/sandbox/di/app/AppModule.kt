package com.uvarov.sandbox.di.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.deserializers.BreedsResponseDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(BreedsResponse::class.java, BreedsResponseDeserializer())

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        return retrofit.create(DogService::class.java)
    }
}