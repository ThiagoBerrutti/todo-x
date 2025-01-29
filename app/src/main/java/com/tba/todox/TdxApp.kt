package com.tba.todox

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tba.todox.home.navigation.HOME_SCREEN_ROUTE
import com.tba.todox.navigation.TdxNavHost
import com.tba.todox.navigation.TdxNavHostState
import com.tba.todox.navigation.TopDestination
import com.tba.todox.ui.HomeAddTaskModalBottomSheet
import com.tba.todox.ui.theme.ToDoXTheme


@Composable
fun TdxApp(
    appState: TdxAppState,
    modifier: Modifier = Modifier,
) {
    val fabWidth = remember { 64.dp }
    var showModal by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = { TdxTopAppBar() },
        bottomBar = { TdxBottomNavigationBar(fabWidth = fabWidth) },
        floatingActionButton = {
            BottomBarFab(
                width = fabWidth,
                onClick = { showModal = true }
            )
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
//            val s = TdxNavHostState(appState, "test")
            val s = TdxNavHostState(appState, HOME_SCREEN_ROUTE)

            TdxNavHost(state = s)

            if (showModal) {
                val ctx = LocalContext.current
                HomeAddTaskModalBottomSheet(
                    onDismissRequest = { showModal = false },
                    onSend = {
                        Toast.makeText(
                            ctx,
                            "${it.title} - ${it.description}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                )

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
fun TdxBottomNavigationBar(modifier: Modifier = Modifier, fabWidth: Dp) {
    NavigationBar(modifier = modifier) {
        val destinations = TopDestination.entries.toList()
        destinations.mapIndexed { index, dest ->
            if (index == 2) {
                Box(modifier = Modifier.size(fabWidth)) { }
            }

            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(id = dest.icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = dest.label, color = Color.White) },
            )
        }

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


@Preview
@Composable
private fun AppPreview() {
    val context = LocalContext.current
    val state = TdxAppState(navController = NavHostController(context))
    ToDoXTheme {
        TdxApp(appState = state)
    }

}