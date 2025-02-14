package com.tba.todox.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.tba.todox.core.designsystem.R


val latoFontFamily = FontFamily(
    Font(R.font.lato_black, FontWeight.Black),
    Font(R.font.lato_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.lato_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_thin, FontWeight.Thin),
    Font(R.font.lato_thin_italic, FontWeight.Thin, FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.5.em,
    ),
    bodySmall = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = (1.5).em
    ),
    displayLarge = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 20.sp,
    ),
)

