package com.vardhanharsh.mywallet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    background = Surface,
    surface = Surface,
    onPrimary = textPrimary,
    onSecondary = textPrimary,
    onBackground = textPrimary,
    onSurface = textPrimary,
    error = Destructive,
    onError = textPrimary
)

//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
//)

@Composable
fun MyWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
//        else -> LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}