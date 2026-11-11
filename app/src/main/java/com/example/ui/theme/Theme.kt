package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DxdColorScheme = darkColorScheme(
    primary = DxdNeonRed,
    onPrimary = Color.White,
    primaryContainer = DxdDarkRedContainer,
    onPrimaryContainer = DxdBrightRed,
    secondary = DxdBrightRed,
    onSecondary = Color.White,
    secondaryContainer = DxdDarkRedContainer,
    onSecondaryContainer = DxdNeonRedLight,
    tertiary = DxdNeonGold,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF2E2207),
    onTertiaryContainer = DxdNeonGold,
    background = DxdPureBlack,
    onBackground = DxdTextPrimary,
    surface = DxdDarkObsidian,
    onSurface = DxdTextPrimary,
    surfaceVariant = DxdSurfaceVariant,
    onSurfaceVariant = DxdTextSecondary,
    outline = DxdRedGlowBorder,
    outlineVariant = Color(0xFF2A2D3C),
    error = Color(0xFFFF5252),
    onError = Color.White
)

@Composable
fun DxdGamingTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DxdColorScheme,
        typography = Typography,
        content = content
    )
}
