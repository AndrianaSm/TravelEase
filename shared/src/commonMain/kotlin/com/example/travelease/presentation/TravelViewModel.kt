package com.example.travelease.presentation

import androidx.lifecycle.ViewModel
import com.example.travelease.data.model.AssistanceLevel
import com.example.travelease.data.model.Category
import com.example.travelease.data.model.Confidence
import com.example.travelease.data.model.Difficulty
import com.example.travelease.data.model.TicketType
import com.example.travelease.data.model.TravelTask
import com.example.travelease.data.model.calculateAssistanceLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TravelUiState(
    val category: Category = Category.TRANSPORT,
    val task: TravelTask = TravelTask.TICKET,
    val difficulty: Difficulty? = null,
    val confidence: Confidence? = null,
    val assistanceLevel: AssistanceLevel? = null,
    val ticketType: TicketType = TicketType.ONE_WAY,
)

class TravelViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(TravelUiState())
    val state = mutableState.asStateFlow()

    fun selectCategory(category: Category) = mutableState.update { it.copy(category = category) }
    fun selectTask(task: TravelTask) = mutableState.update { it.copy(task = task) }
    fun selectDifficulty(value: Difficulty) = mutableState.update {
        it.copy(difficulty = value, assistanceLevel = null)
    }
    fun selectConfidence(value: Confidence) = mutableState.update {
        it.copy(confidence = value, assistanceLevel = null)
    }
    fun selectTicketType(value: TicketType) = mutableState.update { it.copy(ticketType = value) }

    fun calculateLevel(): AssistanceLevel? {
        val current = state.value
        val difficulty = current.difficulty ?: return null
        val confidence = current.confidence ?: return null
        val level = calculateAssistanceLevel(difficulty, confidence)
        mutableState.update { it.copy(assistanceLevel = level) }
        return level
    }
}
