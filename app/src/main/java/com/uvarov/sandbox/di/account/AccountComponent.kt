package com.uvarov.sandbox.di.account

import com.uvarov.sandbox.ui.account.AccountFragment
import dagger.Subcomponent

@AccountScope
@Subcomponent(modules = [AccountModule::class])
interface AccountComponent {

    fun inject(accountFragment: AccountFragment)
}