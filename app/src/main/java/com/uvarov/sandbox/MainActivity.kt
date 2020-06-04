package com.uvarov.sandbox

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.uvarov.sandbox.account.AccountManager
import com.uvarov.sandbox.account.GoogleAccountFactory
import com.uvarov.sandbox.databinding.MainActivityBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var accountManager: AccountManager

    private lateinit var viewBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as SandboxApplication).appComponent.createMainComponent().inject(this)

        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        accountManager.login(GoogleAccountFactory(GoogleSignIn.getLastSignedInAccount(this)).createAccount())

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        @IdRes
        val startDestination: Int = if (accountManager.account != null) R.id.breedsListFragment else R.id.loginFragment
        initNavGraph(navHostFragment, startDestination)

        setupNavView(navHostFragment)
    }

    private fun initNavGraph(navHostFragment: NavHostFragment, @IdRes startDestinationId: Int) {
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph: NavGraph = graphInflater.inflate(R.navigation.nav_graph)
        navGraph.startDestination = startDestinationId
        navHostFragment.navController.graph = navGraph
    }

    private fun setupNavView(navHostFragment: NavHostFragment) {
        viewBinding.navigation.setupWithNavController(navHostFragment.navController)
    }
}