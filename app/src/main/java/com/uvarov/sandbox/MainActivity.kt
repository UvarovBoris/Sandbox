package com.uvarov.sandbox

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.account.GoogleAccountFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as SandboxApplication).appComponent.createMainComponent().inject(this)

        accountManager.login(GoogleAccountFactory(GoogleSignIn.getLastSignedInAccount(this)).createAccount())

        setContentView(R.layout.main_activity)

        @IdRes
        val startDestination: Int = if (accountManager.account != null) R.id.breedsListFragment else R.id.loginFragment
        initNavGraph(startDestination)
    }

    private fun initNavGraph(@IdRes startDestinationId: Int) {
        val navHost: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val graphInflater = navHost.navController.navInflater
        val navGraph: NavGraph = graphInflater.inflate(R.navigation.nav_graph)
        navGraph.startDestination = startDestinationId
        navHost.navController.graph = navGraph
    }
}