package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import ru.arturmineev9.dailyplanner.core.common.datetime.DateTimeUtils
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model.HourSlot
import java.time.LocalTime

@Composable
fun DailyTimeline(
    selectedDate: Long,
    hourSlots: List<HourSlot>,
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(hourSlots) {
        if (hourSlots.isNotEmpty() && DateTimeUtils.isToday(selectedDate)) {
            val currentHour = LocalTime.now().hour
            listState.animateScrollToItem(currentHour)
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        itemsIndexed(
            items = hourSlots,
            key = { _, slot -> slot.timeLabel }
        ) { index, slot ->
            HourCell(
                hourSlot = slot,
                onTaskClick = onTaskClick,
                isCurrentHour = DateTimeUtils.isToday(selectedDate) && index == LocalTime.now().hour
            )
        }
    }
}
