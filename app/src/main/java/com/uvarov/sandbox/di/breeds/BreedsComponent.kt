package com.uvarov.sandbox.di.breeds

import com.uvarov.sandbox.ui.breeds.BreedsFragment
import dagger.Subcomponent

@BreedsScope
@Subcomponent(modules = [BreedsModule::class])
interface BreedsComponent {

    fun inject(breedsFragment: BreedsFragment)
}