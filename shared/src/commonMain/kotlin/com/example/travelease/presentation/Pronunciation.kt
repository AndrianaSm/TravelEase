package com.example.travelease.presentation

interface PronunciationPlayer {
    fun speak(text: String, languageTag: String): PronunciationResult
}

enum class PronunciationResult { STARTED, UNAVAILABLE }

class UnavailablePronunciationPlayer : PronunciationPlayer {
    override fun speak(text: String, languageTag: String) = PronunciationResult.UNAVAILABLE
}
