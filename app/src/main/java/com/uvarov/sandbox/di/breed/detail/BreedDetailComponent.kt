package com.uvarov.sandbox.di.breed.detail

import com.uvarov.sandbox.ui.breed.detail.BreedDetailFragment
import dagger.Subcomponent

@BreedDetailScope
@Subcomponent(modules = [BreedDetailModule::class])
interface BreedDetailComponent {

    fun inject(breedDetailFragment: BreedDetailFragment)
}