package com.uvarov.sandbox.account

class AccountManager {

    var account: Account? = null
        private set

    fun login(account: Account?) {
        this.account = account
    }

    fun logout() {
        account = null
    }
}