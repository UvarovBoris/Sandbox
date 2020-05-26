package com.uvarov.sandbox.di.breed.list

import com.uvarov.sandbox.ui.breed.list.BreedsListFragment
import dagger.Subcomponent

@BreedsListScope
@Subcomponent(modules = [BreedsListModule::class])
interface BreedsListComponent {

    fun inject(breedsListFragment: BreedsListFragment)
}