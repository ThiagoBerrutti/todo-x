package com.tba.todox.navigation

import androidx.annotation.DrawableRes
import com.tba.todox.R
import com.tba.todox.feature.home.navigation.CalendarRoute
import com.tba.todox.feature.home.navigation.FocusRoute
import com.tba.todox.feature.home.navigation.HomeScreenRoute
import com.tba.todox.feature.home.navigation.ProfileRoute
import kotlin.reflect.KClass



enum class TopDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route:KClass<*>,
    val label: String,
) {
    HOME(
        selectedIcon = R.drawable.home_2_bold,
        unselectedIcon =R.drawable.home_2_outline,
        route = HomeScreenRoute::class,
        label = "Index",
    ),
    CALENDAR(
        selectedIcon =R.drawable.calendar_bold,
        unselectedIcon =R.drawable.calendar_outline,
        route = CalendarRoute::class,
        label = "Calendar",
    ),
    FOCUS(
        selectedIcon =R.drawable.clock_bold,
        unselectedIcon =R.drawable.clock_outline,
        route = FocusRoute::class,
        label = "Focus",
    ),
    PROFILE(
        selectedIcon =R.drawable.user_bold,
        unselectedIcon =R.drawable.user_outline,
        route = ProfileRoute::class,
        label = "Profile",
    ),
}