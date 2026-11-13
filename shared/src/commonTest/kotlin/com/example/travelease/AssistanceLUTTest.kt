package com.example.travelease

import com.example.travelease.fuzzy.AssistanceLUT
import com.example.travelease.fuzzy.AssistanceLayout
import kotlin.test.Test
import kotlin.test.assertEquals

class AssistanceLUTTest {
    @Test
    fun lowDifficultyProducesLowAssistanceAtEveryTimePressureGridPoint() {
        for (timePressure in 0..10) {
            assertEquals(
                AssistanceLayout.LOW,
                AssistanceLUT.queryFromIntegerScale(1, timePressure),
                "Time pressure: $timePressure",
            )
        }
    }

    @Test
    fun maximumDifficultyAndNoTimePressureProducesHighAssistance() {
        assertEquals(AssistanceLayout.HIGH, AssistanceLUT.resolveLayout(AssistanceLUT.evaluate(1f, 0f)))
    }

    @Test
    fun maximumDifficultyAndMaximumTimePressureProducesModerateAssistance() {
        assertEquals(AssistanceLayout.MODERATE, AssistanceLUT.resolveLayout(AssistanceLUT.evaluate(1f, 1f)))
    }

    @Test
    fun halfDifficultyAndLowTimePressureUsesExpectedLookupValue() {
        assertEquals(0.5f, AssistanceLUT.evaluate(0.5f, 0.2f), 0.0001f)
    }

    @Test
    fun halfDifficultyAndMaximumTimePressureUsesExpectedLookupValue() {
        assertEquals(0.1873f, AssistanceLUT.evaluate(0.5f, 1f), 0.0001f)
    }

    @Test
    fun intermediateInputsUseBilinearInterpolation() {
        assertEquals(0.54315f, AssistanceLUT.evaluate(0.55f, 0.25f), 0.0001f)
    }

    @Test
    fun inputsOutsideRangeAreClamped() {
        assertEquals(AssistanceLUT.evaluate(0f, 0.5f), AssistanceLUT.evaluate(-1f, 0.5f))
        assertEquals(AssistanceLUT.evaluate(1f, 1f), AssistanceLUT.evaluate(2f, 3f))
    }

    @Test
    fun exactClassificationBoundariesAreModerate() {
        assertEquals(AssistanceLayout.MODERATE, AssistanceLUT.resolveLayout(0.35f))
        assertEquals(AssistanceLayout.MODERATE, AssistanceLUT.resolveLayout(0.65f))
        assertEquals(AssistanceLayout.LOW, AssistanceLUT.resolveLayout(0.3499f))
        assertEquals(AssistanceLayout.HIGH, AssistanceLUT.resolveLayout(0.6501f))
    }
}
