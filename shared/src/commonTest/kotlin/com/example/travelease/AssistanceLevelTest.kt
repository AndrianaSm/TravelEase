package com.example.travelease

import com.example.travelease.data.model.*
import com.example.travelease.presentation.TravelViewModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AssistanceLevelTest {
    @Test
    fun allAnswerCombinationsMapToExpectedLevels() {
        val expected = mapOf(
            (Difficulty.EASY to Confidence.NOT_CONFIDENT) to AssistanceLevel.MODERATE,
            (Difficulty.EASY to Confidence.SOMEWHAT_CONFIDENT) to AssistanceLevel.LOW,
            (Difficulty.EASY to Confidence.VERY_CONFIDENT) to AssistanceLevel.LOW,
            (Difficulty.MODERATE to Confidence.NOT_CONFIDENT) to AssistanceLevel.HIGH,
            (Difficulty.MODERATE to Confidence.SOMEWHAT_CONFIDENT) to AssistanceLevel.MODERATE,
            (Difficulty.MODERATE to Confidence.VERY_CONFIDENT) to AssistanceLevel.MODERATE,
            (Difficulty.DIFFICULT to Confidence.NOT_CONFIDENT) to AssistanceLevel.HIGH,
            (Difficulty.DIFFICULT to Confidence.SOMEWHAT_CONFIDENT) to AssistanceLevel.HIGH,
            (Difficulty.DIFFICULT to Confidence.VERY_CONFIDENT) to AssistanceLevel.HIGH,
        )
        for ((answers, level) in expected) {
            assertEquals(level, calculateAssistanceLevel(answers.first, answers.second), "$answers")
        }
    }

    @Test
    fun answersAreRequiredAndRetained() {
        val model = TravelViewModel()
        assertNull(model.calculateLevel())
        model.selectDifficulty(Difficulty.EASY)
        assertNull(model.calculateLevel())
        model.selectConfidence(Confidence.VERY_CONFIDENT)
        assertEquals(AssistanceLevel.LOW, model.calculateLevel())
        assertEquals(Difficulty.EASY, model.state.value.difficulty)
        assertEquals(Confidence.VERY_CONFIDENT, model.state.value.confidence)
        model.selectConfidence(Confidence.NOT_CONFIDENT)
        assertNull(model.state.value.assistanceLevel)
        assertEquals(AssistanceLevel.MODERATE, model.calculateLevel())
    }
}
