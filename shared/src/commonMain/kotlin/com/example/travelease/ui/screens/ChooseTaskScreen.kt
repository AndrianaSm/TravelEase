package com.example.travelease.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.travelease.data.model.Category
import com.example.travelease.data.model.TravelTask
import com.example.travelease.ui.components.*
import com.example.travelease.ui.theme.TravelMuted

@Composable
fun ChooseTaskScreen(category: Category, onBack: () -> Unit, onTask: (TravelTask) -> Unit) {
    ScreenLayout("Choose a task", onBack) {
        ScreenHeading("What do you need?", category.label)
        if (category != Category.TRANSPORT) {
            Text("Help for this category is coming soon. Please choose Public transport for now.", color = TravelMuted, style = MaterialTheme.typography.bodyMedium)
        } else {
            TravelTask.entries.forEach { task ->
                val icon = when (task) {
                    TravelTask.TICKET -> Icons.Default.ConfirmationNumber
                    TravelTask.PLATFORM -> Icons.Default.Train
                    TravelTask.SYMBOL -> Icons.Default.Signpost
                    TravelTask.ANNOUNCEMENT -> Icons.Default.Campaign
                    TravelTask.DIRECTIONS -> Icons.Default.Directions
                    TravelTask.JOURNEY -> Icons.Default.Route
                }
                TaskCard(task.title, task.description, icon) { onTask(task) }
            }
        }
    }
}
