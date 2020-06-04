package com.uvarov.sandbox.di.drawer

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.di.ViewModelKey
import com.uvarov.sandbox.ui.account.AccountViewModel
import com.uvarov.sandbox.ui.drawer.DrawerViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DrawerModule {

    @Provides
    @IntoMap
    @ViewModelKey(DrawerViewModel::class)
    fun provideDrawerViewModel(accountManager: AccountManager): ViewModel {
        return DrawerViewModel(accountManager)
    }
}