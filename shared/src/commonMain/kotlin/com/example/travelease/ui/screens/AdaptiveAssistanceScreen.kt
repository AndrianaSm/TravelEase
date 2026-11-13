package com.example.travelease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelease.data.model.Confidence
import com.example.travelease.data.model.Difficulty
import com.example.travelease.presentation.TravelUiState
import com.example.travelease.ui.components.*

private data class Option<T>(val value: T, val title: String, val description: String, val icon: ImageVector)

private val difficulties = listOf(
    Option(Difficulty.EASY, "Easy", "I understand signs and instructions.", Icons.Default.SentimentSatisfied),
    Option(Difficulty.MODERATE, "Moderate", "I understand some things, but still need help.", Icons.Default.SentimentNeutral),
    Option(Difficulty.DIFFICULT, "Difficult", "I don't understand the signs or instructions.", Icons.Default.SentimentDissatisfied),
)

private val confidences = listOf(
    Option(Confidence.NOT_CONFIDENT, "Not confident", "I don't know what to do.", Icons.Default.SentimentDissatisfied),
    Option(Confidence.SOMEWHAT_CONFIDENT, "Somewhat confident", "I understand some of it, but need guidance.", Icons.Default.SentimentNeutral),
    Option(Confidence.VERY_CONFIDENT, "Very confident", "I can manage, but would like some help.", Icons.Default.SentimentSatisfied),
)

@Composable
private fun <T> Question(title: String, options: List<Option<T>>, selected: T?, onSelect: (T) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            if (maxWidth < 320.dp || LocalDensity.current.fontScale > 1.3f) {
                Column(Modifier.fillMaxWidth().selectableGroup(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    options.forEach { option ->
                        AssistanceOptionCard(option.title, option.description, option.icon, option.value == selected, { onSelect(option.value) }, Modifier.fillMaxWidth())
                    }
                }
            } else {
                Row(Modifier.fillMaxWidth().selectableGroup(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    options.forEach { option ->
                        AssistanceOptionCard(option.title, option.description, option.icon, option.value == selected, { onSelect(option.value) }, Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun AdaptiveAssistanceScreen(
    state: TravelUiState,
    onBack: () -> Unit,
    onDifficulty: (Difficulty) -> Unit,
    onConfidence: (Confidence) -> Unit,
    onContinue: () -> Unit,
) {
    ScreenLayout("Tailored help", onBack) {
        ScreenHeading("Let's find the right level of help.", "Choose what feels right for you.")
        Question("How difficult is it to understand this situation?", difficulties, state.difficulty, onDifficulty)
        Question("How confident do you feel handling this situation?", confidences, state.confidence, onConfidence)
        PrimaryButton("Get tailored help", onContinue, enabled = state.difficulty != null && state.confidence != null)
    }
}
