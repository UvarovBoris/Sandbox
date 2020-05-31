package com.uvarov.sandbox.account

class AccountManager {

    var account: Account? = null
        private set

    val isLoggedIn: Boolean
        get() = account != null

    fun logIn(account: Account) {
        this.account = account
    }

    fun logOut() {
        account = null
    }
}