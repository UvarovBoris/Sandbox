package com.uvarov.sandbox.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.Account
import com.uvarov.sandbox.account.AccountManager

class AccountViewModel(private val accountManager: AccountManager) : ViewModel() {

    private val _accountLD: MutableLiveData<Account?> = MutableLiveData()
    val accountLD: LiveData<Account?> get() = _accountLD

    fun requestAccount() {
        _accountLD.value = accountManager.account
    }

    fun logOut() {
        accountManager.logout()
    }
}