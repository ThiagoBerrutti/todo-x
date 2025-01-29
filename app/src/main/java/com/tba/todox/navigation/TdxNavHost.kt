package com.tba.todox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.compose.NavHost
import com.tba.todox.TdxAppState
import com.tba.todox.home.navigation.homeScreen

@Stable
data class TdxNavHostState(
    val appState: TdxAppState,
    val startDestination: String,
)

@Composable
fun TdxNavHost(state: TdxNavHostState) {
    NavHost(
        navController = state.appState.navController,
        startDestination = state.startDestination
    ) {
        homeScreen()
    }
}