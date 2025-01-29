package com.tba.todox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.tba.todox.ui.theme.ToDoXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoXTheme(true, false) {
                val navController = rememberNavController()
                val appState = rememberTdxAppState(navController = navController)
                TdxApp(appState = appState)
            }
        }
    }
}
