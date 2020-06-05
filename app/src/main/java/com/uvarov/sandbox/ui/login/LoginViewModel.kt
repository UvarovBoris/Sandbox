package com.uvarov.sandbox.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.Account
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.utils.SingleEvent

class LoginViewModel(private val accountManager: AccountManager) : ViewModel() {

    private val _moveBreedsLD: MutableLiveData<SingleEvent<Account>> = MutableLiveData()
    val moveBreedsLD: LiveData<SingleEvent<Account>> get() = _moveBreedsLD

    fun handleLogIn(account: Account) {
        accountManager.login(account)
        accountManager.account?.let {
            _moveBreedsLD.value = SingleEvent(it)
        }
    }
}