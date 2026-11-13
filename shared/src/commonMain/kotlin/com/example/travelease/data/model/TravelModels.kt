package com.example.travelease.data.model

enum class Category(val label: String) {
    TRANSPORT("Public transport"),
    FOOD("Food and restaurants"),
    DIRECTIONS("Directions"),
    SHOPPING("Shopping"),
    OTHER("Other situations"),
}

enum class TravelTask(val title: String, val description: String) {
    TICKET("Buy a train ticket", "Purchase your ticket before boarding."),
    PLATFORM("Find the right platform", "Locate your departure platform."),
    SYMBOL("Understand a transport symbol", "See a symbol and what it means."),
    ANNOUNCEMENT("Understand an announcement", "Decode audio and platform messages."),
    DIRECTIONS("Ask for directions", "Get help navigating the station."),
    JOURNEY("Follow my journey", "See where to stop, the next stop, and when to get off."),
}

enum class AssistanceLevel { LOW, MODERATE, HIGH }
enum class TicketType(val label: String) { ONE_WAY("One-way"), RETURN("Return") }

data class Translation(
    val title: String,
    val targetLanguage: String,
    val targetText: String,
    val sourceLanguage: String,
    val sourceText: String,
    val pronunciation: String,
    val category: String,
)

val sampleTranslation = Translation(
    title = "Buy a train ticket",
    targetLanguage = "German",
    targetText = "Ich möchte ein Ticket nach Bern, bitte.",
    sourceLanguage = "English",
    sourceText = "I'd like a ticket to Bern, please.",
    pronunciation = "ich MERKH-tuh ine TIK-et nah BEHRN, BIT-tuh",
    category = "Train",
)
