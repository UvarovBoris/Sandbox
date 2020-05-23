package com.uvarov.sandbox.di.breeds

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.breeds.BreedsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class BreedsModule {

    @Provides
    @IntoMap
    @ViewModelKey(BreedsViewModel::class)
    fun provideBreedsViewModel(dogService: DogService): ViewModel {
        return BreedsViewModel(dogService)
    }
}