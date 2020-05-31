package com.uvarov.sandbox.di.login

import com.uvarov.sandbox.ui.login.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)
}