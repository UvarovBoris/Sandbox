package com.uvarov.sandbox.di.app

import com.uvarov.sandbox.di.account.AccountComponent
import com.uvarov.sandbox.di.account.AccountModule
import com.uvarov.sandbox.di.breed.detail.BreedDetailComponent
import com.uvarov.sandbox.di.breed.detail.BreedDetailModule
import com.uvarov.sandbox.di.breed.list.BreedsListComponent
import com.uvarov.sandbox.di.breed.list.BreedsListModule
import com.uvarov.sandbox.di.login.LoginComponent
import com.uvarov.sandbox.di.login.LoginModule
import com.uvarov.sandbox.di.main.MainComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun createMainComponent(): MainComponent

    fun createBreedsListComponent(breedsListModule: BreedsListModule): BreedsListComponent

    fun createBreedDetailComponent(breedDetailModule: BreedDetailModule): BreedDetailComponent

    fun createLoginComponent(loginModule: LoginModule): LoginComponent

    fun createAccountComponent(accountModule: AccountModule): AccountComponent
}