package com.uvarov.sandbox

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment

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

        GoogleSignIn.getLastSignedInAccount(this)?.let {
            accountManager.login(GoogleAccountFactory(it).createAccount())
        }

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        @IdRes
        val startDestination: Int = if (accountManager.account != null) R.id.breedsNavigation else R.id.loginNavigation
        initNavGraph(navHostFragment, startDestination)
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    private fun initNavGraph(navHostFragment: NavHostFragment, @IdRes startDestinationId: Int) {
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph: NavGraph = graphInflater.inflate(R.navigation.nav_graph)
        navGraph.startDestination = startDestinationId
        navHostFragment.navController.graph = navGraph
    }

    fun disableDrawer() {
        viewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    fun enableDrawer() {
        viewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
    }

    fun isDrawerOpen(): Boolean {
        return viewBinding.drawerLayout.isDrawerOpen(viewBinding.drawerContainer)
    }

    fun openDrawer() {
        viewBinding.drawerLayout.openDrawer(viewBinding.drawerContainer)
    }

    fun closeDrawer() {
        viewBinding.drawerLayout.closeDrawer(viewBinding.drawerContainer)
    }
}