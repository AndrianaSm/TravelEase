package com.example.travelease.presentation

import androidx.lifecycle.ViewModel
import com.example.travelease.data.model.AssistanceLevel
import com.example.travelease.data.model.Category
import com.example.travelease.data.model.TicketType
import com.example.travelease.data.model.TravelTask
import com.example.travelease.fuzzy.AssistanceLUT
import com.example.travelease.fuzzy.AssistanceLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TravelUiState(
    val category: Category = Category.TRANSPORT,
    val task: TravelTask = TravelTask.TICKET,
    val difficulty: Int = 5,
    val timePressure: Int = 5,
    val assistanceScore: Float? = null,
    val assistanceLevel: AssistanceLevel? = null,
    val ticketType: TicketType = TicketType.ONE_WAY,
)

class TravelViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(TravelUiState())
    val state = mutableState.asStateFlow()

    fun selectCategory(category: Category) = mutableState.update { it.copy(category = category) }
    fun selectTask(task: TravelTask) = mutableState.update { it.copy(task = task) }
    fun updateDifficulty(value: Int) = mutableState.update {
        it.copy(difficulty = value.coerceIn(0, 10), assistanceScore = null, assistanceLevel = null)
    }
    fun updateTimePressure(value: Int) = mutableState.update {
        it.copy(timePressure = value.coerceIn(0, 10), assistanceScore = null, assistanceLevel = null)
    }
    fun selectTicketType(value: TicketType) = mutableState.update { it.copy(ticketType = value) }

    fun calculateAssistance(): AssistanceLevel? {
        val current = state.value
        val score = AssistanceLUT.evaluate(
            current.difficulty / 10f,
            current.timePressure / 10f,
        )
        val level = when (AssistanceLUT.queryFromIntegerScale(current.difficulty, current.timePressure)) {
            AssistanceLayout.LOW -> AssistanceLevel.LOW
            AssistanceLayout.MODERATE -> AssistanceLevel.MODERATE
            AssistanceLayout.HIGH -> AssistanceLevel.HIGH
        }
        mutableState.update { it.copy(assistanceScore = score, assistanceLevel = level) }
        return level
    }
}
