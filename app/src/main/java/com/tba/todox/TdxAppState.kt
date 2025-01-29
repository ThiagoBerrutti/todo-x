package com.tba.todox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun rememberTdxAppState(navController: NavHostController): TdxAppState {
    return remember(navController) {
        TdxAppState(navController)
    }
}

@Stable
data class TdxAppState(
    val navController: NavHostController,
)