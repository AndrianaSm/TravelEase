package com.example.travelease.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import com.example.travelease.data.model.Category
import com.example.travelease.ui.components.*

@Composable
fun HomeScreen(selected: Category, onCategory: (Category) -> Unit) {
    ScreenLayout(null, null) {
        ScreenHeading("What do you need help with?", "Get help in a language you understand.")
        Category.entries.forEach { category ->
            val icon = when (category) {
                Category.TRANSPORT -> Icons.Default.Train
                Category.FOOD -> Icons.Default.Restaurant
                Category.DIRECTIONS -> Icons.Default.Place
                Category.SHOPPING -> Icons.Default.ShoppingBag
                Category.OTHER -> Icons.Default.MoreHoriz
            }
            CategoryCard(category.label, icon, category == selected) { onCategory(category) }
        }
    }
}
