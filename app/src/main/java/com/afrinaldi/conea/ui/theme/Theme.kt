package com.afrinaldi.conea.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//    surface = SmoothDark
//)

private val LightColorPalette = lightColors(
    primary = PrimaryDark,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = LightWhite

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ConeaTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = PrimaryDark
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}