package com.valmiraguiar.gifapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AccentBlue,
    secondary = AccentMint,
    tertiary = AccentSand,
    background = NightInk,
    surface = NightSlate,
    surfaceVariant = NightPanel,
    onPrimary = NightInk,
    onSecondary = NightInk,
    onTertiary = NightInk,
    onBackground = GlassWhite,
    onSurface = GlassWhite,
    onSurfaceVariant = GlassWhite.copy(alpha = 0.72f)
)

private val LightColorScheme = lightColorScheme(
    primary = NightSlate,
    secondary = AccentBlue,
    tertiary = AccentSand,
    background = DaySky,
    surface = DayMist,
    surfaceVariant = GlassWhite,
    onPrimary = GlassWhite,
    onSecondary = NightInk,
    onTertiary = NightInk,
    onBackground = NightInk,
    onSurface = NightInk,
    onSurfaceVariant = NightInk.copy(alpha = 0.68f)
)

@Composable
fun GIFAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
