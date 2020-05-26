package com.uvarov.sandbox.di.app

import com.uvarov.sandbox.di.breed.detail.BreedDetailComponent
import com.uvarov.sandbox.di.breed.detail.BreedDetailModule
import com.uvarov.sandbox.di.breed.list.BreedsListComponent
import com.uvarov.sandbox.di.breed.list.BreedsListModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun createBreedsListComponent(breedsListModule: BreedsListModule): BreedsListComponent

    fun createBreedDetailComponent(breedDetailModule: BreedDetailModule): BreedDetailComponent
}