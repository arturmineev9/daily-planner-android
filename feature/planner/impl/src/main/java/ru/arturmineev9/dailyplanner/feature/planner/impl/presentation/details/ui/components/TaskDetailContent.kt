package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.model.TaskDetailInfo

@Composable
fun TaskDetailContent(
    info: TaskDetailInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = info.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        IconTextRow(
            icon = Icons.Default.CalendarToday,
            text = info.dateFull
        )

        IconTextRow(
            icon = Icons.Default.Schedule,
            text = info.timeRange
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Описание:",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = info.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
