package com.example.travelease.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelease.data.model.TicketType
import com.example.travelease.data.model.Translation
import com.example.travelease.ui.theme.*

@Composable
fun ScreenLayout(title: String?, onBack: (() -> Unit)?, content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().background(Color.White).safeDrawingPadding()) {
        AppTopBar(title, onBack)
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content,
        )
    }
}

@Composable
fun AppTopBar(title: String?, onBack: (() -> Unit)?) {
    Row(
        Modifier.fillMaxWidth().heightIn(min = 60.dp).padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(title.orEmpty(), style = MaterialTheme.typography.labelLarge, color = TravelMuted, modifier = Modifier.weight(1f))
            Text("TravelEase", color = TravelBlue, fontWeight = FontWeight.Bold)
        } else {
            Text("TravelEase", color = TravelBlue, fontWeight = FontWeight.ExtraBold, fontSize = 21.sp, modifier = Modifier.weight(1f))
            Text("Home", color = TravelMuted, style = MaterialTheme.typography.labelLarge)
        }
    }
    HorizontalDivider(color = TravelBorder)
}

@Composable
fun ScreenHeading(title: String, subtitle: String? = null) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TravelText, modifier = Modifier.semantics { heading() })
        if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TravelMuted)
    }
}

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, enabled: Boolean = true, icon: ImageVector? = null) {
    Button(onClick = onClick, enabled = enabled, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp), shape = RoundedCornerShape(12.dp)) {
        if (icon != null) { Icon(icon, null); Spacer(Modifier.width(8.dp)) }
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun SecondaryButton(text: String, onClick: () -> Unit, icon: ImageVector? = null) {
    OutlinedButton(onClick = onClick, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp), shape = RoundedCornerShape(12.dp), border = BorderStroke(1.dp, TravelBorder)) {
        if (icon != null) { Icon(icon, null); Spacer(Modifier.width(8.dp)) }
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun CategoryCard(title: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().heightIn(min = 68.dp)
            .semantics { this.selected = selected }
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = if (selected) TravelBlueLight else TravelSurface,
        border = if (selected) BorderStroke(1.5.dp, TravelBlue) else null,
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = TravelBlue)
            Spacer(Modifier.width(14.dp))
            Text(title, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            if (selected) Icon(Icons.Default.CheckCircle, contentDescription = "Selected", tint = TravelBlue, modifier = Modifier.size(20.dp))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TravelMuted)
        }
    }
}

@Composable
fun TaskCard(title: String, description: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp), color = Color.White, border = BorderStroke(1.dp, TravelBorder),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = TravelBlue, modifier = Modifier.size(26.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(description, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TravelMuted)
        }
    }
}

@Composable
fun AssistanceOptionCard(title: String, description: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.heightIn(min = 160.dp).selectable(selected = selected, onClick = onClick, role = Role.RadioButton),
        shape = RoundedCornerShape(14.dp), color = if (selected) TravelBlueLight else TravelSurface,
        border = BorderStroke(if (selected) 2.dp else 1.dp, if (selected) TravelBlue else TravelBorder),
    ) {
        Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = TravelBlue, modifier = Modifier.size(24.dp))
                Spacer(Modifier.weight(1f))
                if (selected) Icon(Icons.Default.CheckCircle, contentDescription = "Selected", tint = TravelBlue, modifier = Modifier.size(18.dp))
            }
            Text(title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
            Text(description, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
        }
    }
}

@Composable
fun TranslationCard(translation: Translation) {
    Surface(shape = RoundedCornerShape(14.dp), color = TravelSurface, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(translation.targetLanguage.uppercase(), style = MaterialTheme.typography.labelSmall, color = TravelBlue, fontWeight = FontWeight.Bold)
            Text(translation.targetText, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(color = TravelBorder, modifier = Modifier.padding(vertical = 4.dp))
            Text(translation.sourceLanguage.uppercase(), style = MaterialTheme.typography.labelSmall, color = TravelMuted, fontWeight = FontWeight.Bold)
            Text(translation.sourceText, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun RecommendationBadge(label: String, description: String, color: Color = TravelBlue) {
    Surface(shape = RoundedCornerShape(12.dp), color = TravelSurface, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, color = color, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            Text(description, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun TransportCategoryChip(category: String) {
    Surface(shape = RoundedCornerShape(50), color = TravelBlueLight) {
        Row(Modifier.padding(horizontal = 12.dp, vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Train, null, tint = TravelBlue, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(category, color = TravelBlue, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DestinationCard(selected: TicketType, onSelect: (TicketType) -> Unit) {
    Surface(shape = RoundedCornerShape(14.dp), color = TravelSurface, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, null, tint = TravelBlue)
                Spacer(Modifier.width(8.dp))
                Text("Destination: Bern", fontWeight = FontWeight.Bold)
            }
            Text("Ticket type", style = MaterialTheme.typography.labelMedium, color = TravelMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TicketType.entries.forEach { type ->
                    FilterChip(selected = selected == type, onClick = { onSelect(type) }, label = { Text(type.label) }, modifier = Modifier.heightIn(min = 48.dp))
                }
            }
        }
    }
}

@Composable
fun InstructionStep(number: Int, title: String, description: String, icon: ImageVector) {
    Surface(shape = RoundedCornerShape(14.dp), color = TravelSurface, modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
            Surface(shape = RoundedCornerShape(50), color = TravelBlueLight) {
                Box(Modifier.size(32.dp), contentAlignment = Alignment.Center) {
                    Text("$number", color = TravelBlue, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                Text(description, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
            }
            Icon(icon, null, tint = TravelBlue, modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
fun TransportSymbolCard(name: String, description: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Surface(modifier = modifier, shape = RoundedCornerShape(14.dp), color = TravelSurface, border = BorderStroke(1.dp, TravelBorder)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(shape = RoundedCornerShape(12.dp), color = TravelBlue) {
                Icon(icon, null, tint = Color.White, modifier = Modifier.padding(12.dp).size(32.dp))
            }
            Text(name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(description, style = MaterialTheme.typography.bodySmall, color = TravelMuted)
        }
    }
}
