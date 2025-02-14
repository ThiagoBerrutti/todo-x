package com.tba.todox

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tba.todox.feature.home.navigation.AddTaskRoute
import com.tba.todox.navigation.TdxNavHost
import com.tba.todox.navigation.TopDestination
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.reflect.KClass

fun Long.toFormattedTimestamp(
    pattern: String = "dd/MM/yyyy HH:mm",
    locale: Locale = Locale.getDefault(),
): String =
    SimpleDateFormat(pattern, locale)
        .format(Date(this))


@Composable
fun TdxApp(
    appState: TdxAppState,
    modifier: Modifier = Modifier,
) {
    val fabWidth = remember { 64.dp }
    val currentDestination = appState.currentDestination
    var showScaffold by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TdxTopAppBar() },
        bottomBar = {
            if (showScaffold) {
                NavigationBar {
                    appState.topDestinations.forEachIndexed { index, destination ->
                        val selected =
                            currentDestination.isRouteInHierarchy(destination.route)

                        if (index == 2) {
                            Box(modifier = Modifier.size(fabWidth)) { }
                        }

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                appState.navigateToTopDestination(destination)
                            },
                            icon = {
                                Icon(
                                    painter = if (selected) {
                                        painterResource(destination.selectedIcon)
                                    } else {
                                        painterResource(destination.unselectedIcon)
                                    },
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = { Text(text = destination.label, color = Color.White) },
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (showScaffold) {
                BottomBarFab(
                    width = fabWidth,
                    onClick = {
                        appState.navController.navigate(AddTaskRoute)
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .consumeWindowInsets(WindowInsets(0, 0, 0, 0))
            ) {
                TdxNavHost(appState)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TdxTopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp),
    ) {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Index",
                    style = MaterialTheme.typography.titleMedium,
                    letterSpacing = (-0.5).sp
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = null
                )
            },
            actions = {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                )
            }
        )
    }
}


@Composable
fun TdxBottomNavigationBar(
    modifier: Modifier = Modifier,
    onCalendarClick: () -> Unit,
    fabWidth: Dp,
    items: @Composable RowScope.() -> Unit,
) {
    NavigationBar(modifier = modifier) {
//        val destinations = TopDestination.entries.toList()
        items()
//        destinations.mapIndexed { index, dest ->
//            if (index == 2) {
//                Box(modifier = Modifier.size(fabWidth)) { }
//            }


//            NavigationBarItem(
//                selected = false,
//                onClick = { if (index == 1) onCalendarClick() },
//                icon = {
//                    if (selected)
//                        Icon(
//                            painter = painterResource(id = dest.icon),
//                            contentDescription = null,
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                },
//                label = { Text(text = dest.label, color = Color.White) },
//            )
//        }

    }
}

@NonRestartableComposable
@Composable
fun BottomBarFab(width: Dp, onClick: () -> Unit = {}) {
    val offset by remember { derivedStateOf { width / 2 + 16.dp } }

    FloatingActionButton(
        modifier = Modifier
            .size(width)
            .offset { IntOffset(0, offset.roundToPx()) },
        onClick = onClick,
        shape = CircleShape,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

private fun NavDestination?.isRouteInHierarchy(destination: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(destination)
    } ?: false
