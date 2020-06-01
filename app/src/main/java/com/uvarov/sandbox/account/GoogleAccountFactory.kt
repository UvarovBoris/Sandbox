package com.uvarov.sandbox.account

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class GoogleAccountFactory(private val googleSignInAccount: GoogleSignInAccount?) : AccountFactory {

    override fun createAccount(): Account? {
        return googleSignInAccount?.let {
            Account(
                googleSignInAccount.id,
                googleSignInAccount.givenName,
                googleSignInAccount.familyName,
                googleSignInAccount.photoUrl
            )
        }
    }
}