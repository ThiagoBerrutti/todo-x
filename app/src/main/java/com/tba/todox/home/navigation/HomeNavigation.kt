package com.tba.todox.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tba.todox.home.HomeScreen


const val HOME_SCREEN_ROUTE = "home_screen_route"

fun NavGraphBuilder.homeScreen(){
    composable(route = HOME_SCREEN_ROUTE) {
        HomeScreen()
    }
}