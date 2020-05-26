package com.uvarov.sandbox.di.breed.detail

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.breed.detail.BreedDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class BreedDetailModule {

    @Provides
    @IntoMap
    @ViewModelKey(BreedDetailViewModel::class)
    fun provideBreedsViewModel(dogService: DogService): ViewModel {
        return BreedDetailViewModel(dogService)
    }
}