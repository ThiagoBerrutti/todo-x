package com.tba.todox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.tba.todox.calendar.navigateToCalendar
import com.tba.todox.feature.home.navigation.navigateToHome
import com.tba.todox.navigation.TopDestination

@Composable
fun rememberTdxAppState(navController: NavHostController): TdxAppState {
    return remember(navController) {
        TdxAppState(navController)
    }
}

@Stable
class TdxAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topDestinations: List<TopDestination> = TopDestination.entries

    fun navigateToTopDestination(topDestination: TopDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topDestination) {
            TopDestination.HOME -> {
                navController.navigateToHome(navOptions)
            }

            TopDestination.CALENDAR -> {
                navController.navigateToCalendar(navOptions)
            }

            TopDestination.FOCUS -> {
                navController.navigateToTest1(navOptions)
            }

            TopDestination.PROFILE -> {
                navController.navigateToTest2(navOptions)
            }
        }

    }
}