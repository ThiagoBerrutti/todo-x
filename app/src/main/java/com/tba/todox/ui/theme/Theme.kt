package com.tba.todox.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple1,
    secondary = Color.Red,
    tertiary = Color.Yellow,
//    surface = Color.Green,

    secondaryContainer = Color.Magenta,

//    secondary = Purple1,
//    tertiary = Purple1,
//    surfaceVariant = Color.Red,
//    surface = Color.Red,
    error = Color.Red,
//    onTertiaryContainer = Color.Red,

//    surfaceContainerLow = Color.Red,
    surfaceContainerLow = GrayDark,

//    inverseOnSurface= Color.Red,
//    onErrorContainer = Color.Red,
//    onSurfaceVariant = Color.Red,
//    surfaceVariant = Color.Red,


    primaryContainer = Purple1,
    onPrimary = Color.White,
//    surfaceContainer = GrayDark,

//    onPrimaryContainer = Color.Red,

//    surfaceContainer = Color.Red,
//
//    onSecondaryContainer = Color.Red,
//    surfaceContainerHigh = Color.Red,
//    tertiaryContainer = Color.Red,
//    onPrimaryContainer = Color.Red,
//    secondaryContainer = Color.Red,
//    errorContainer = Color.Red,
//    inversePrimary = Color.Red,
//    inverseSurface = Color.Red,
//    outlineVariant = Color.Red,
//    scrim = Color.Red,
//    onError = Color.Red,
//    outline = Color.Red,
//    onPrimary = Color.Red,
//    onSurface = Color.Red,

    background = Black1,

//    onTertiary = Color.Red,
//    surfaceDim = Color.Red,
//    onBackground = Color.Red,
//    surfaceBright = Color.Red,
//    onSecondary = Color.Red,
//    surfaceTint = Color.Red,
//    surfaceContainerLowest = Color.Red,
//    surfaceContainerHighest = Color.Red
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Red,
    secondary = Color.Red,
    tertiary = Color.Red,
    surfaceVariant = Color.Red,
    surface = Color.Red,
    error = Color.Red,
    onTertiaryContainer = Color.Red,
    surfaceContainerLow = Color.Red,
    inverseOnSurface= Color.Red,
    onErrorContainer = Color.Red,
    onSurfaceVariant = Color.Red,
    primaryContainer = Color.Red,
    surfaceContainer = Color.Red,
    onSecondaryContainer = Color.Red,
    surfaceContainerHigh = Color.Red,
    tertiaryContainer = Color.Red,
    onPrimaryContainer = Color.Red,
    secondaryContainer = Color.Red,
    errorContainer = Color.Red,
    inversePrimary = Color.Red,
    inverseSurface = Color.Red,
    outlineVariant = Color.Red,
    scrim = Color.Red,
    onError = Color.Red,
    outline = Color.Red,
    onPrimary = Color.Red,
    onSurface = Color.Red,
    background = Color.Red,
    onTertiary = Color.Red,
    surfaceDim = Color.Red,
    onBackground = Color.Red,
    surfaceBright = Color.Red,
    onSecondary = Color.Red,
    surfaceTint = Color.Red,
    surfaceContainerLowest = Color.Red,
    surfaceContainerHighest = Color.Red

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ToDoXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}