package com.tba.todox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tba.todox.feature.home.navigation.FocusRoute
import com.tba.todox.feature.home.navigation.ProfileRoute


@Composable
fun Test1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("CONTENT")
    }
}


fun NavController.navigateToTest1(navOptions: NavOptions?) {
    navigate(FocusRoute, navOptions)
}

fun NavGraphBuilder.test1() {
    composable<FocusRoute> { Test1() }
}


///////////////////////


@Composable
fun Test2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pagina 2")
    }
}

fun NavController.navigateToTest2(navOptions: NavOptions?) {
    navigate(ProfileRoute, navOptions)
}

fun NavGraphBuilder.test2() {
    composable<ProfileRoute> { Test2() }
}