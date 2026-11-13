package com.example.travelease

import com.example.travelease.data.model.AssistanceLevel
import com.example.travelease.data.model.Category
import com.example.travelease.data.model.TicketType
import com.example.travelease.data.model.TravelTask
import com.example.travelease.presentation.TravelViewModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AssistanceLevelTest {
    @Test
    fun questionnaireStartsAtMidpoint() {
        val model = TravelViewModel()

        assertEquals(5, model.state.value.difficulty)
        assertEquals(5, model.state.value.timePressure)
        assertNull(model.state.value.assistanceScore)
        assertNull(model.state.value.assistanceLevel)
    }

    @Test
    fun sliderUpdatesChangeTheirValuesAndInvalidatePreviousResult() {
        val model = TravelViewModel()
        model.calculateAssistance()

        model.updateDifficulty(8)
        assertEquals(8, model.state.value.difficulty)
        assertNull(model.state.value.assistanceScore)
        assertNull(model.state.value.assistanceLevel)

        model.calculateAssistance()
        model.updateTimePressure(2)
        assertEquals(2, model.state.value.timePressure)
        assertNull(model.state.value.assistanceScore)
        assertNull(model.state.value.assistanceLevel)
    }

    @Test
    fun calculationStoresFuzzyScoreAndMappedAssistanceLevel() {
        val model = TravelViewModel()

        assertEquals(AssistanceLevel.MODERATE, model.calculateAssistance())
        assertEquals(0.5f, model.state.value.assistanceScore)
        assertEquals(AssistanceLevel.MODERATE, model.state.value.assistanceLevel)

        model.updateDifficulty(10)
        model.updateTimePressure(0)
        assertEquals(AssistanceLevel.HIGH, model.calculateAssistance())
        assertEquals(0.8127f, model.state.value.assistanceScore)
    }

    @Test
    fun sliderValuesAreClampedToEngineInputRange() {
        val model = TravelViewModel()

        model.updateDifficulty(-1)
        model.updateTimePressure(12)

        assertEquals(0, model.state.value.difficulty)
        assertEquals(10, model.state.value.timePressure)
    }

    @Test
    fun categoryTaskAndTicketSelectionsArePreserved() {
        val model = TravelViewModel()
        model.selectCategory(Category.FOOD)
        model.selectTask(TravelTask.DIRECTIONS)
        model.selectTicketType(TicketType.RETURN)

        model.updateDifficulty(7)
        model.updateTimePressure(3)
        model.calculateAssistance()

        assertEquals(Category.FOOD, model.state.value.category)
        assertEquals(TravelTask.DIRECTIONS, model.state.value.task)
        assertEquals(TicketType.RETURN, model.state.value.ticketType)
    }
}
