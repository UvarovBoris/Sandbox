package com.uvarov.sandbox.di.login

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginModule {

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideLoginViewModel(accountManager: AccountManager): ViewModel {
        return LoginViewModel(accountManager)
    }
}