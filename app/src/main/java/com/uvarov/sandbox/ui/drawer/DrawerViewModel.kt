package com.uvarov.sandbox.ui.drawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.account.Account
import com.uvarov.sandbox.account.AccountManager
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DrawerViewModel(private val accountManager: AccountManager) : ViewModel() {

    private val _accountLD: MutableLiveData<Account?> = MutableLiveData()
    val accountLD: LiveData<Account?> get() = _accountLD

    private val disposables: CompositeDisposable = CompositeDisposable()

    init {
        accountManager.loginSubject
            .subscribe { _accountLD.value = it }
            .let { disposables.add(it) }
    }

    fun requestAccount() {
        _accountLD.value = accountManager.account
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}