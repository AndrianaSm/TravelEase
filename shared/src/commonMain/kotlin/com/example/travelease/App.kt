package com.example.travelease

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelease.data.model.*
import com.example.travelease.presentation.*
import com.example.travelease.ui.components.ScreenHeading
import com.example.travelease.ui.components.ScreenLayout
import com.example.travelease.ui.screens.*
import com.example.travelease.ui.theme.TravelEaseTheme
import com.example.travelease.ui.theme.TravelMuted
import kotlin.reflect.KClass

private object Routes {
    const val HOME = "home"
    const val TASKS = "tasks"
    const val ASSESSMENT = "assessment"
    const val LOW = "low"
    const val MODERATE = "moderate"
    const val HIGH = "high"
    const val TRANSLATION = "translation"
    const val SYMBOLS = "symbols"
    const val PLACEHOLDER = "placeholder"
}

private object TravelViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: KClass<T>,
        extras: CreationExtras,
    ): T {
        require(modelClass == TravelViewModel::class) {
            "TravelViewModelFactory can only create TravelViewModel."
        }
        @Suppress("UNCHECKED_CAST")
        return TravelViewModel() as T
    }
}

@Composable
fun App() {
    TravelEaseTheme {
        val model: TravelViewModel = viewModel(factory = TravelViewModelFactory)
        val state by model.state.collectAsStateWithLifecycle()
        val nav = rememberNavController()
        val pronunciation: PronunciationPlayer = remember { UnavailablePronunciationPlayer() }
        var showPhrase by remember { mutableStateOf(false) }
        var showSpeechUnavailable by remember { mutableStateOf(false) }
        val translation = sampleTranslation
        val back: () -> Unit = { nav.popBackStack() }
        val hear: () -> Unit = {
            when (pronunciation.speak(translation.targetText, "de")) {
                PronunciationResult.STARTED -> Unit
                PronunciationResult.UNAVAILABLE -> showSpeechUnavailable = true
            }
        }
        val show: () -> Unit = { showPhrase = true }
        val openTranslation: () -> Unit = { nav.navigate(Routes.TRANSLATION) { launchSingleTop = true } }

        NavHost(navController = nav, startDestination = Routes.HOME) {
            composable(Routes.HOME) {
                HomeScreen(state.category) { category ->
                    model.selectCategory(category)
                    nav.navigate(Routes.TASKS) { launchSingleTop = true }
                }
            }
            composable(Routes.TASKS) {
                ChooseTaskScreen(state.category, back) { task ->
                    model.selectTask(task)
                    val destination = when (task) {
                        TravelTask.TICKET -> Routes.ASSESSMENT
                        TravelTask.SYMBOL -> Routes.SYMBOLS
                        else -> Routes.PLACEHOLDER
                    }
                    nav.navigate(destination) { launchSingleTop = true }
                }
            }
            composable(Routes.ASSESSMENT) {
                AdaptiveAssistanceScreen(state, back, model::updateDifficulty, model::updateTimePressure) {
                    val destination = when (model.calculateAssistance()) {
                        AssistanceLevel.LOW -> Routes.LOW
                        AssistanceLevel.MODERATE -> Routes.MODERATE
                        AssistanceLevel.HIGH -> Routes.HIGH
                        null -> null
                    }
                    if (destination != null) nav.navigate(destination) { launchSingleTop = true }
                }
            }
            composable(Routes.LOW) {
                LowAssistanceScreen(state, translation, back, hear, show, openTranslation)
            }
            composable(Routes.MODERATE) {
                ModerateAssistanceScreen(state, translation, back, model::selectTicketType, hear, openTranslation)
            }
            composable(Routes.HIGH) {
                HighAssistanceScreen(
                    state,
                    translation,
                    back,
                    model::selectTicketType,
                    hear,
                    openTranslation,
                    onFinish = {
                        nav.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable(Routes.TRANSLATION) {
                TranslationScreen(translation, back, hear, show) {
                    if (!nav.popBackStack(Routes.ASSESSMENT, false)) {
                        nav.navigate(Routes.ASSESSMENT) { launchSingleTop = true }
                    }
                }
            }
            composable(Routes.SYMBOLS) { TransportSymbolsScreen(back) }
            composable(Routes.PLACEHOLDER) {
                ScreenLayout(state.task.title, back) {
                    ScreenHeading(state.task.title)
                    Text("Guidance for this task is coming soon. Please choose another task for now.", color = TravelMuted)
                }
            }
        }

        if (showSpeechUnavailable) {
            AlertDialog(
                onDismissRequest = { showSpeechUnavailable = false },
                title = { Text("Pronunciation unavailable") },
                text = { Text("Audio playback is not available yet. You can show the phrase to someone instead.") },
                confirmButton = { TextButton(onClick = { showSpeechUnavailable = false }) { Text("OK") } },
            )
        }
        if (showPhrase) {
            AlertDialog(
                onDismissRequest = { showPhrase = false },
                title = { Text(translation.targetLanguage) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        Text(translation.targetText, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text(translation.sourceText, style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
                    }
                },
                confirmButton = { TextButton(onClick = { showPhrase = false }) { Text("Close") } },
            )
        }
    }
}
