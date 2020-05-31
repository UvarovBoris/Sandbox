package com.uvarov.sandbox.account

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class GoogleAccountFactory(val googleSignInAccount: GoogleSignInAccount?) : AccountFactory {

    override fun createAccount(): Account {
        return Account("123")
    }
}