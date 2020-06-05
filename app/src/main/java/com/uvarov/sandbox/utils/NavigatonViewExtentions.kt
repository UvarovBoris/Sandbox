package com.uvarov.sandbox.utils

import android.os.Bundle
import android.view.ViewParent
import androidx.annotation.IdRes
import androidx.customview.widget.Openable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference

fun NavigationView.setupWithNavController(navController: NavController) {
    setNavigationItemSelectedListener { item ->
        val handled = NavigationUI.onNavDestinationSelected(item, navController)
        if (handled) {
            var parent: ViewParent? = parent
            while (parent != null) {
                if (parent is Openable) {
                    parent.close()
                    break
                }
                parent = parent.parent
            }
        }
        handled
    }
    val weakReference = WeakReference(this)
    navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
        override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
            val view = weakReference.get()
            if (view == null) {
                navController.removeOnDestinationChangedListener(this)
                return
            }
            val menu = view.menu
            for (i in 0 until menu.size()) {
                val item = menu.getItem(i)
                item.isChecked = matchDestination(destination, item.itemId)
            }
        }
    })
}

private fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}