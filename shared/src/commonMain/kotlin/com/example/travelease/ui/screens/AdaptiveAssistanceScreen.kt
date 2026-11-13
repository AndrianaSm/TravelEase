package com.example.travelease.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import com.example.travelease.presentation.TravelUiState
import com.example.travelease.ui.components.PrimaryButton
import com.example.travelease.ui.components.ScreenHeading
import com.example.travelease.ui.components.ScreenLayout
import com.example.travelease.ui.theme.TravelBlue
import com.example.travelease.ui.theme.TravelMuted
import com.example.travelease.ui.theme.TravelSurface

@Composable
private fun AssistanceSlider(
    question: String,
    value: Int,
    startLabel: String,
    endLabel: String,
    description: String,
    accessibilityLabel: String,
    onValueChange: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = TravelSurface,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(question, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Slider(
                value = value.toFloat(),
                onValueChange = { onValueChange(it.roundToInt()) },
                valueRange = 0f..10f,
                steps = 9,
                modifier = Modifier.semantics {
                    contentDescription = accessibilityLabel
                    stateDescription = value.toString()
                },
                colors = SliderDefaults.colors(
                    thumbColor = TravelBlue,
                    activeTrackColor = TravelBlue,
                ),
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                (0..10).forEach { step ->
                    Text(
                        text = step.toString(),
                        modifier = Modifier.weight(1f),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = TravelMuted,
                        maxLines = 1,
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(startLabel, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
                Text(endLabel, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
            }
            Text("Selected: $value", style = MaterialTheme.typography.titleSmall, color = TravelBlue, fontWeight = FontWeight.Bold)
            Text(description, style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
        }
    }
}

private fun difficultyDescription(value: Int): String = when {
    value <= 3 -> "I understand what I need to do."
    value <= 6 -> "I understand some things, but need guidance."
    else -> "I find this situation difficult."
}

private fun timePressureDescription(value: Int): String = when {
    value <= 3 -> "I have enough time to understand and decide."
    value <= 6 -> "I feel a little rushed."
    else -> "I need help quickly."
}

@Composable
fun AdaptiveAssistanceScreen(
    state: TravelUiState,
    onBack: () -> Unit,
    onDifficulty: (Int) -> Unit,
    onTimePressure: (Int) -> Unit,
    onContinue: () -> Unit,
) {
    ScreenLayout("Tailored help", onBack) {
        ScreenHeading("Let's find the right level of help.", "Choose what feels right for you.")
        AssistanceSlider(
            question = "How difficult is it to understand this situation?",
            value = state.difficulty,
            startLabel = "Easy",
            endLabel = "Difficult",
            description = difficultyDescription(state.difficulty),
            accessibilityLabel = "Difficulty, from easy to difficult",
            onValueChange = onDifficulty,
        )
        AssistanceSlider(
            question = "How much time pressure do you feel?",
            value = state.timePressure,
            startLabel = "No pressure",
            endLabel = "Very urgent",
            description = timePressureDescription(state.timePressure),
            accessibilityLabel = "Time pressure, from no pressure to very urgent",
            onValueChange = onTimePressure,
        )
        PrimaryButton("Get tailored help", onContinue)
    }
}
