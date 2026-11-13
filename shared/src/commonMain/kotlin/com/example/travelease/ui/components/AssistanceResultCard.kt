package com.example.travelease.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelease.data.model.AssistanceLevel
import com.example.travelease.presentation.TravelUiState
import com.example.travelease.ui.theme.TravelMuted

private val LowAssistanceAccent = Color(0xFF16A34A)
private val ModerateAssistanceAccent = Color(0xFFD97706)
private val HighAssistanceAccent = Color(0xFFDC2626)

private fun AssistanceLevel.accentColor(): Color = when (this) {
    AssistanceLevel.LOW -> LowAssistanceAccent
    AssistanceLevel.MODERATE -> ModerateAssistanceAccent
    AssistanceLevel.HIGH -> HighAssistanceAccent
}

private fun AssistanceLevel.icon(): ImageVector = when (this) {
    AssistanceLevel.LOW -> Icons.Default.CheckCircle
    AssistanceLevel.MODERATE -> Icons.Default.Info
    AssistanceLevel.HIGH -> Icons.Default.Warning
}

@Composable
fun AssistanceLevelBanner(
    level: AssistanceLevel,
    title: String,
    subtitle: String,
) {
    val accent = level.accentColor()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Surface(
            shape = RoundedCornerShape(50),
            color = accent.copy(alpha = 0.12f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.35f)),
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(level.icon(), contentDescription = null, tint = accent)
                Text(
                    "${level.name} ASSISTANCE",
                    color = accent,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = accent,
        )
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
    }
}

@Composable
fun AssistanceResultCard(state: TravelUiState) {
    val level = state.assistanceLevel
    val accent = level?.accentColor() ?: TravelMuted
    val score = state.assistanceScore?.let(::formatAssistanceScore) ?: "Not available"
    val levelLabel = level?.name ?: "Not available"

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = accent.copy(alpha = 0.06f),
        border = BorderStroke(1.dp, accent.copy(alpha = 0.2f)),
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                "YOUR ASSISTANCE RESULT",
                color = accent,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
            )
            ResultRow("Difficulty", "${state.difficulty} / 10")
            ResultRow("Time pressure", "${state.timePressure} / 10")
            ResultRow("Fuzzy assistance score", score)
            ResultRow("Assistance level", levelLabel, accent)
        }
    }
}

@Composable
private fun ResultRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
        Text(
            value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = valueColor,
        )
    }
}

private fun formatAssistanceScore(score: Float): String {
    val rounded = (score * 1000).toInt()
    return "${rounded / 1000}.${(rounded % 1000).toString().padStart(3, '0')}"
}
