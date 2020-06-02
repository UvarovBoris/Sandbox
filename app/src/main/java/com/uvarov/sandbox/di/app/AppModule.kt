package com.uvarov.sandbox.di.app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.api.BreedImagesResponse
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.deserializers.BreedImagesResponseDeserializer
import com.uvarov.sandbox.deserializers.BreedsResponseDeserializer
import com.uvarov.sandbox.FlipperInitializer
import com.uvarov.sandbox.StethoInitializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider


@Module
class AppModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }

    @AppScope
    @Provides
    fun provideDogService(): DogService {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        StethoInitializer.initNetworkInterceptor(okHttpClientBuilder)
        FlipperInitializer.initNetworkInterceptor(okHttpClientBuilder)

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(BreedsResponse::class.java, BreedsResponseDeserializer())
        gsonBuilder.registerTypeAdapter(BreedImagesResponse::class.java, BreedImagesResponseDeserializer())

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        return retrofit.create(DogService::class.java)
    }

    @AppScope
    @Provides
    fun provideAccountManager(): AccountManager {
        return AccountManager()
    }
}