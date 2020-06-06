package com.uvarov.sandbox.utils

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewParent
import androidx.annotation.IdRes
import androidx.customview.widget.Openable
import androidx.navigation.*
import com.google.android.material.navigation.NavigationView
import com.uvarov.sandbox.R
import java.lang.ref.WeakReference

fun NavigationView.setupWithNavController(navController: NavController) {
    setNavigationItemSelectedListener { item ->
        val handled = onNavDestinationSelected(item, navController)
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

private fun onNavDestinationSelected(item: MenuItem, navController: NavController): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    if (item.order and Menu.CATEGORY_SECONDARY == 0) {
        builder.setPopUpTo(findStartOfDestination(navController.graph, item.itemId).id, false)
    }
    val options = builder.build()
    return try {
        //TODO provide proper API instead of using Exceptions as Control-Flow.
        navController.navigate(item.itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

private fun findStartOfDestination(graph: NavGraph, @IdRes destinationId: Int): NavDestination {
    for (subGraph: NavDestination in graph) {
        if (subGraph is NavGraph) {
            if (subGraph.contains(destinationId)) {
                return findStartDestination(subGraph)!!
            }
        }
    }
    return findStartDestination(graph)!!
}

fun findStartDestination(graph: NavGraph): NavDestination? {
    return graph.findNode(graph.startDestination)
}

private fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}