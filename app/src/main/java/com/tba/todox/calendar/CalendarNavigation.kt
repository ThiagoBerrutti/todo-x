package com.tba.todox.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tba.todox.feature.home.navigation.CalendarRoute

fun NavController.navigateToCalendar(navOptions: NavOptions) = navigate(CalendarRoute, navOptions)

fun NavGraphBuilder.calendarScreen(){
    composable<CalendarRoute> {
        CalendarScreen()
    }
}