package com.uvarov.sandbox.di.breed.list

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.breed.list.BreedsListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class BreedsListModule {

    @Provides
    @IntoMap
    @ViewModelKey(BreedsListViewModel::class)
    fun provideBreedsListViewModel(dogService: DogService): ViewModel {
        return BreedsListViewModel(dogService)
    }
}