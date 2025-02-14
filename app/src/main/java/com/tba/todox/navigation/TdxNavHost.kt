package com.tba.todox.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.tba.todox.TdxAppState
import com.tba.todox.calendar.calendarScreen
import com.tba.todox.feature.home.add_task.addTask
import com.tba.todox.feature.home.navigation.HomeScreenRoute
import com.tba.todox.feature.home.navigation.homeScreen
import com.tba.todox.test1
import com.tba.todox.test2

@Composable
fun TdxNavHost(appState: TdxAppState) {
    NavHost(
        navController = appState.navController,
        startDestination = HomeScreenRoute
    ) {
        homeScreen()
        calendarScreen()
        test2()
        test1()
        addTask { appState.navController.navigateUp() }
    }
}
