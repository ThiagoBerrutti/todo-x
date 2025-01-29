package com.tba.todox.navigation

import androidx.annotation.DrawableRes
import com.tba.todox.R


enum class TopDestination(val route: String, val label: String, @DrawableRes val icon: Int) {
    HOME_SCREEN_ROUTE(
        route = "HOME_SCREEN_ROUTE",
        label = "Charts",
        icon = R.drawable.home_2
    ),
    CALENDAR_SCREEN_ROUTE(
        route = "CALENDAR_SCREEN_ROUTE",
        label = "Calendar",
        icon = R.drawable.calendar_1
    ),
    FOCUS_SCREEN_ROUTE(
        route = "FOCUS_SCREEN_ROUTE",
        label = "Focus",
        icon = R.drawable.clock
    ),
    PROFILE_SCREEN_ROUTE(
        route = "PROFILE_SCREEN_ROUTE",
        label = "Profile",
        icon = R.drawable.user
    ),
}