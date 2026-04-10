package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.model.HourSlot

@Composable
fun HourCell(
    modifier: Modifier = Modifier,
    hourSlot: HourSlot,
    onTaskClick: (Int) -> Unit,
    isCurrentHour: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = hourSlot.timeLabel,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (isCurrentHour) FontWeight.ExtraBold else FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(90.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (hourSlot.tasks.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(Modifier, thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                hourSlot.tasks.forEach { task ->
                    TaskCard(
                        task = task,
                        onClick = { onTaskClick(task.id) }
                    )
                }
            }
        }
    }
}
