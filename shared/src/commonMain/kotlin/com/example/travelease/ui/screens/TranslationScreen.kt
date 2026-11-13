package com.example.travelease.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.PresentToAll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.travelease.data.model.Translation
import com.example.travelease.ui.components.*
import com.example.travelease.ui.theme.TravelMuted

@Composable
fun TranslationScreen(translation: Translation, onBack: () -> Unit, onHear: () -> Unit, onShow: () -> Unit, onMoreHelp: () -> Unit) {
    ScreenLayout("Translation", onBack) {
        ScreenHeading(translation.title, "Use this phrase to ask for your ticket.")
        TranslationCard(translation)
        Text("Listen to the phrase, or show it to someone at the station.", style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
        TransportCategoryChip(translation.category)
        PrimaryButton("Hear pronunciation", onHear, icon = Icons.AutoMirrored.Filled.VolumeUp)
        SecondaryButton("Show phrase to someone", onShow, Icons.Default.PresentToAll)
        SecondaryButton("I need more help", onMoreHelp)
    }
}
