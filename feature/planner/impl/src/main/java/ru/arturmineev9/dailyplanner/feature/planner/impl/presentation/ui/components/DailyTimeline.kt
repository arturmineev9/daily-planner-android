package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model.HourSlot

@Composable
fun DailyTimeline(
    hourSlots: List<HourSlot>,
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = hourSlots,
            key = { slot -> slot.timeLabel }
        ) { slot ->
            HourCell(
                hourSlot = slot,
                onTaskClick = onTaskClick
            )
        }
    }
}
