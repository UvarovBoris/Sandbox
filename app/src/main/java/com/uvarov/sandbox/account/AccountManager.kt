package com.uvarov.sandbox.account

import io.reactivex.rxjava3.subjects.PublishSubject

class AccountManager {

    val loginSubject: PublishSubject<Account> = PublishSubject.create()

    var account: Account? = null
        private set

    fun login(account: Account) {
        this.account = account
        loginSubject.onNext(account)
    }

    fun logout() {
        account = null
    }
}