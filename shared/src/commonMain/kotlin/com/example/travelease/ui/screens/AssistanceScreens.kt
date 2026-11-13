package com.example.travelease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelease.data.model.*
import com.example.travelease.presentation.TravelUiState
import com.example.travelease.ui.components.*
import com.example.travelease.ui.theme.TravelMuted

private fun difficultyLabel(value: Difficulty?): String = when (value) {
    Difficulty.EASY -> "Easy"
    Difficulty.MODERATE -> "Moderate"
    Difficulty.DIFFICULT -> "Difficult"
    null -> "Not selected"
}

private fun confidenceLabel(value: Confidence?): String = when (value) {
    Confidence.NOT_CONFIDENT -> "Not confident"
    Confidence.SOMEWHAT_CONFIDENT -> "Somewhat confident"
    Confidence.VERY_CONFIDENT -> "Very confident"
    null -> "Not selected"
}

@Composable
fun LowAssistanceScreen(state: TravelUiState, translation: Translation, onBack: () -> Unit, onHear: () -> Unit, onShow: () -> Unit, onTranslation: () -> Unit) {
    ScreenLayout("Tailored help", onBack) {
        Text("LOW ASSISTANCE", color = Color(0xFF167347), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        ScreenHeading("Low assistance", "A phrase to get you started.")
        RecommendationBadge("FROM ADAPTIVE ASSISTANCE", "${difficultyLabel(state.difficulty)} difficulty + ${confidenceLabel(state.confidence)}", Color(0xFF167347))
        TranslationCard(translation)
        TransportCategoryChip(translation.category)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Say it slowly", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(translation.pronunciation, style = MaterialTheme.typography.bodyMedium)
            Text("Approximate sounds for English speakers.", style = MaterialTheme.typography.bodySmall, color = TravelMuted)
        }
        PrimaryButton("Hear pronunciation", onHear, icon = Icons.AutoMirrored.Filled.VolumeUp)
        SecondaryButton("Show phrase to someone", onShow, Icons.Default.PresentToAll)
        SecondaryButton("View translation", onTranslation)
    }
}

@Composable
fun ModerateAssistanceScreen(state: TravelUiState, translation: Translation, onBack: () -> Unit, onTicketType: (TicketType) -> Unit, onHear: () -> Unit, onTranslation: () -> Unit) {
    ScreenLayout("Tailored help", onBack) {
        ScreenHeading("Moderate assistance")
        RecommendationBadge("MODERATE — FROM ADAPTIVE ASSISTANCE", "${difficultyLabel(state.difficulty)} difficulty + ${confidenceLabel(state.confidence)}", Color(0xFF915600))
        TranslationCard(translation)
        DestinationCard(state.ticketType, onTicketType)
        TransportCategoryChip(translation.category)
        Text("Steps to buy your ticket", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        InstructionStep(1, "Select Bern.", "Search or tap the destination field.", Icons.Default.Place)
        InstructionStep(2, "Choose one-way or return.", "Select the ticket type.", Icons.Default.ConfirmationNumber)
        InstructionStep(3, "Pay and collect your ticket.", "Follow the payment options shown.", Icons.Default.Payment)
        PrimaryButton("Hear pronunciation", onHear, icon = Icons.AutoMirrored.Filled.VolumeUp)
        SecondaryButton("View translation", onTranslation)
    }
}

@Composable
fun HighAssistanceScreen(state: TravelUiState, translation: Translation, onBack: () -> Unit, onTicketType: (TicketType) -> Unit, onHear: () -> Unit, onTranslation: () -> Unit) {
    ScreenLayout("Tailored help", onBack) {
        Text("HIGH ASSISTANCE", color = Color(0xFFB34325), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        ScreenHeading("High assistance", "We'll guide you through each step.")
        RecommendationBadge("HIGH — FROM ADAPTIVE ASSISTANCE", "${difficultyLabel(state.difficulty)} difficulty + ${confidenceLabel(state.confidence)}", Color(0xFFB34325))
        TranslationCard(translation)
        DestinationCard(state.ticketType, onTicketType)
        TransportCategoryChip(translation.category)
        Text("Steps to buy your ticket", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        InstructionStep(1, "Find the ticket machine.", "Look for a ticket machine in the station, usually near the entrance or platforms.", Icons.Default.Train)
        InstructionStep(2, "Select your destination.", "Find the destination field. Enter or select Bern.", Icons.Default.Place)
        InstructionStep(3, "Choose a one-way or return ticket.", "One-way is for going there. Return includes the trip back.", Icons.Default.ConfirmationNumber)
        InstructionStep(4, "Pay and collect your ticket.", "Review your choices, follow the payment prompts, and take your ticket.", Icons.Default.Payment)
        PrimaryButton("Hear pronunciation", onHear, icon = Icons.AutoMirrored.Filled.VolumeUp)
        SecondaryButton("View translation", onTranslation)
    }
}
