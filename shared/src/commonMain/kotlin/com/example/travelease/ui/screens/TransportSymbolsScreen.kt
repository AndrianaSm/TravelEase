package com.example.travelease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Tram
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelease.ui.components.*
import com.example.travelease.ui.theme.TravelMuted
import kotlinx.coroutines.launch

private data class Symbol(val name: String, val description: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
private val symbols = listOf(
    Symbol("Train", "Trains travel between stations.", Icons.Default.Train),
    Symbol("Tram", "Trams usually run along city streets.", Icons.Default.Tram),
)

@Composable
fun TransportSymbolsScreen(onBack: () -> Unit) {
    ScreenLayout("Transport symbols", onBack) {
        ScreenHeading("Understand transport symbols")
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val perPage = if (maxWidth >= 500.dp) 2 else 1
            val pageCount = (symbols.size + perPage - 1) / perPage
            val pager = rememberPagerState(pageCount = { pageCount })
            val scope = rememberCoroutineScope()
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                HorizontalPager(state = pager, modifier = Modifier.fillMaxWidth()) { page ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        symbols.drop(page * perPage).take(perPage).forEach { symbol ->
                            TransportSymbolCard(symbol.name, symbol.description, symbol.icon, Modifier.weight(1f))
                        }
                    }
                }
                Text("Page ${pager.currentPage + 1} of $pageCount", style = MaterialTheme.typography.labelMedium, color = TravelMuted)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { scope.launch { pager.animateScrollToPage(pager.currentPage - 1) } }, enabled = pager.currentPage > 0, modifier = Modifier.weight(1f).heightIn(min = 48.dp)) {
                        Text("Previous")
                    }
                    OutlinedButton(onClick = { scope.launch { pager.animateScrollToPage(pager.currentPage + 1) } }, enabled = pager.currentPage < pageCount - 1, modifier = Modifier.weight(1f).heightIn(min = 48.dp)) {
                        Text("Next")
                    }
                }
            }
        }
        Text("A quick way to find the right transport", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text("Use the symbols to quickly understand which transport option is shown on signs, maps, and announcements.", style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
        Text("Swipe through the carousel to see more transport symbols and a simple explanation for each.", style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
    }
}
