package com.example.travelease.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TravelBlue = Color(0xFF2463E8)
val TravelBlueLight = Color(0xFFEAF1FF)
val TravelSurface = Color(0xFFF3F5F8)
val TravelText = Color(0xFF202938)
val TravelMuted = Color(0xFF59677C)
val TravelBorder = Color(0xFFE1E6EF)

private val TravelColors = lightColorScheme(
    primary = TravelBlue,
    onPrimary = Color.White,
    primaryContainer = TravelBlueLight,
    onPrimaryContainer = TravelText,
    background = Color.White,
    onBackground = TravelText,
    surface = Color.White,
    onSurface = TravelText,
    surfaceVariant = TravelSurface,
    onSurfaceVariant = TravelMuted,
    outline = TravelBorder,
)

@Composable
fun TravelEaseTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = TravelColors, typography = Typography(), content = content)
}
