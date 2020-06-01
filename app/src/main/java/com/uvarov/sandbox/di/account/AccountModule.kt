package com.uvarov.sandbox.di.account

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.account.AccountViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class AccountModule {

    @Provides
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun provideAccountViewModel(accountManager: AccountManager): ViewModel {
        return AccountViewModel(accountManager)
    }
}