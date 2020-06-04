package com.uvarov.sandbox.di.drawer

import com.uvarov.sandbox.ui.account.AccountFragment
import com.uvarov.sandbox.ui.drawer.DrawerFragment
import dagger.Subcomponent

@DrawerScope
@Subcomponent(modules = [DrawerModule::class])
interface DrawerComponent {

    fun inject(drawerFragment: DrawerFragment)
}