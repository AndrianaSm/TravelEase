package com.example.travelease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelease.data.model.*
import com.example.travelease.presentation.TravelUiState
import com.example.travelease.ui.components.*
import com.example.travelease.ui.theme.TravelEaseTheme
import com.example.travelease.ui.theme.TravelMuted

@Composable
fun LowAssistanceScreen(state: TravelUiState, translation: Translation, onBack: () -> Unit, onHear: () -> Unit, onShow: () -> Unit, onTranslation: () -> Unit) {
    ScreenLayout("Tailored help", onBack) {
        AssistanceLevelBanner(
            AssistanceLevel.LOW,
            "You can do this!",
            "You're almost there! Here's a little help to get started.",
        )
        AssistanceResultCard(state)
        TranslationCard(translation)
        RecommendationBadge(
            "A LITTLE TIP",
            "Choose Bern as your destination before selecting your ticket type.",
            Color(0xFF16A34A),
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Pronunciation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
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
        AssistanceLevelBanner(
            AssistanceLevel.MODERATE,
            "Let's do it together",
            "Follow these steps to complete your task.",
        )
        AssistanceResultCard(state)
        Text("Your ticket", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        DestinationCard(state.ticketType, onTicketType, Color(0xFFD97706))
        Text("Your quick guide", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        InstructionStep(
            1,
            "Choose Bern",
            "On the ticket machine, search for Bern and confirm the destination.",
            Icons.Default.Place,
            Color(0xFFD97706),
        )
        GermanPhrase("Nach Bern, bitte.", "To Bern, please.", Color(0xFFD97706))
        InstructionStep(
            2,
            "Select your ticket",
            "Choose ${state.ticketType.label.lowercase()} for your journey. You can change the selection above.",
            Icons.Default.ConfirmationNumber,
            Color(0xFFD97706),
        )
        GermanPhrase(
            if (state.ticketType == TicketType.ONE_WAY) "Eine einfache Fahrt, bitte." else "Eine Rückfahrkarte, bitte.",
            if (state.ticketType == TicketType.ONE_WAY) "A one-way ticket, please." else "A return ticket, please.",
            Color(0xFFD97706),
        )
        InstructionStep(
            3,
            "Pay and collect your ticket",
            "Check your selections, follow the payment prompts, and take your ticket.",
            Icons.Default.Payment,
            Color(0xFFD97706),
        )
        PrimaryButton("Hear pronunciation", onHear, icon = Icons.AutoMirrored.Filled.VolumeUp)
        SecondaryButton("View translation", onTranslation)
    }
}

@Composable
fun HighAssistanceScreen(
    state: TravelUiState,
    translation: Translation,
    onBack: () -> Unit,
    onTicketType: (TicketType) -> Unit,
    onHear: () -> Unit,
    onTranslation: () -> Unit,
    onFinish: () -> Unit = onBack,
) {
    var currentStep by remember { mutableIntStateOf(0) }
    val steps = listOf(
        GuidedTicketStep(
            title = "Find a ticket machine",
            instruction = "Look for a ticket machine near the station entrance or platforms. If you cannot find one, ask a staff member.",
            german = "Wo ist der Fahrkartenautomat?",
            english = "Where is the ticket machine?",
            icon = Icons.Default.Train,
            illustrationTitle = "Find the machine",
            illustrationDescription = "Look for a ticket-machine sign near the station entrance.",
        ),
        GuidedTicketStep(
            title = "Choose Bern",
            instruction = "Tap the destination field, search for Bern, and check the station name before continuing.",
            german = "Nach Bern, bitte.",
            english = "To Bern, please.",
            icon = Icons.Default.Place,
            illustrationTitle = "Select destination",
            illustrationDescription = "Tap the destination field and choose Bern from the results.",
        ),
        GuidedTicketStep(
            title = "Choose your ticket",
            instruction = "Select ${state.ticketType.label.lowercase()}. One-way is for the trip there; return includes the trip back. Change the ticket type here if needed.",
            german = if (state.ticketType == TicketType.ONE_WAY) "Eine einfache Fahrt, bitte." else "Eine Rückfahrkarte, bitte.",
            english = if (state.ticketType == TicketType.ONE_WAY) "A one-way ticket, please." else "A return ticket, please.",
            icon = Icons.Default.ConfirmationNumber,
            illustrationTitle = "Select ticket type",
            illustrationDescription = "Choose one-way or return, then confirm the selection.",
        ),
        GuidedTicketStep(
            title = "Pay and collect your ticket",
            instruction = "Review the destination and ticket type. Follow the payment prompts, then take your ticket and any change.",
            german = "Kann ich bitte bezahlen?",
            english = "May I pay, please?",
            icon = Icons.Default.Payment,
            illustrationTitle = "Pay and collect",
            illustrationDescription = "Complete payment and remember to take your ticket.",
        ),
    )

    ScreenLayout("Tailored help", onBack) {
        AssistanceLevelBanner(
            AssistanceLevel.HIGH,
            "We'll guide you through every step.",
            "No worries. Take it one step at a time.",
        )
        AssistanceResultCard(state)
        DestinationCard(state.ticketType, onTicketType, Color(0xFFDC2626))
        LinearProgressIndicator(
            progress = { (currentStep + 1).toFloat() / steps.size },
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFDC2626),
        )
        Text("Step ${currentStep + 1} of ${steps.size}", color = Color(0xFFDC2626), fontWeight = FontWeight.SemiBold)

        if (currentStep < steps.size) {
            val step = steps[currentStep]
            GuidedStepCard(step)
            GermanPhrase(step.german, step.english, Color(0xFFDC2626))
            if (currentStep == steps.lastIndex) {
                RecommendationBadge(
                    "YOU'VE COMPLETED THE STEPS",
                    "Your ticket is ready. Take a moment to check you have it before heading to your platform.",
                    Color(0xFFDC2626),
                )
            }
            PrimaryButton(
                if (currentStep == steps.lastIndex) "Finish guidance" else "Next step",
                onClick = {
                    if (currentStep == steps.lastIndex) {
                        onFinish()
                    } else {
                        currentStep += 1
                    }
                },
                icon = if (currentStep == steps.lastIndex) Icons.Default.CheckCircle else Icons.Default.ArrowForward,
            )
            if (currentStep > 0) {
                SecondaryButton(
                    "Previous step",
                    onClick = { currentStep = (currentStep - 1).coerceAtLeast(0) },
                )
            }
            SecondaryButton("Hear this phrase", onHear, Icons.AutoMirrored.Filled.VolumeUp)
            SecondaryButton("View full translation", onTranslation)
        }
    }
}

private data class GuidedTicketStep(
    val title: String,
    val instruction: String,
    val german: String,
    val english: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val illustrationTitle: String,
    val illustrationDescription: String,
)

@Composable
private fun GuidedStepCard(step: GuidedTicketStep) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFFFF5F5),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Icon(step.icon, contentDescription = null, tint = Color(0xFFDC2626))
                Text(step.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            Text(step.instruction, style = MaterialTheme.typography.bodyLarge)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(step.icon, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(36.dp))
                    Text(step.illustrationTitle, fontWeight = FontWeight.SemiBold)
                    Text(step.illustrationDescription, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
                }
            }
        }
    }
}

@Composable
private fun GermanPhrase(german: String, english: String, accent: Color) {
    RecommendationBadge(
        "USEFUL GERMAN",
        "$german\n$english",
        accent,
    )
}

@Preview
@Composable
private fun LowAssistanceScreenPreview() {
    TravelEaseTheme {
        LowAssistanceScreen(
            state = TravelUiState(
                assistanceScore = 0.1873f,
                assistanceLevel = AssistanceLevel.LOW,
            ),
            translation = sampleTranslation,
            onBack = {},
            onHear = {},
            onShow = {},
            onTranslation = {},
        )
    }
}

@Preview
@Composable
private fun ModerateAssistanceScreenPreview() {
    TravelEaseTheme {
        ModerateAssistanceScreen(
            state = TravelUiState(
                assistanceScore = 0.5f,
                assistanceLevel = AssistanceLevel.MODERATE,
            ),
            translation = sampleTranslation,
            onBack = {},
            onTicketType = {},
            onHear = {},
            onTranslation = {},
        )
    }
}

@Preview
@Composable
private fun HighAssistanceScreenPreview() {
    TravelEaseTheme {
        HighAssistanceScreen(
            state = TravelUiState(
                assistanceScore = 0.8127f,
                assistanceLevel = AssistanceLevel.HIGH,
            ),
            translation = sampleTranslation,
            onBack = {},
            onTicketType = {},
            onHear = {},
            onTranslation = {},
        )
    }
}
