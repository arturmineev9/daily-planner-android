package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.mapper

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.TimeSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.model.HourSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.model.TaskUiModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val LAST_HOUR_OF_DAY = 23
private const val FIRST_HOUR_OF_DAY = 0
private const val NEXT_HOUR_OFFSET = 1
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun TimeSlot.toUiModel(): HourSlot {
    val startHour = String.format(Locale.getDefault(), "%02d:00", hourIndex)
    val nextHourIndex = if (hourIndex == LAST_HOUR_OF_DAY) {
        FIRST_HOUR_OF_DAY
    } else {
        hourIndex + NEXT_HOUR_OFFSET
    }

    val endHour = String.format(Locale.getDefault(), "%02d:00", nextHourIndex)

    return HourSlot(
        timeLabel = "$startHour - $endHour",
        tasks = this.tasks.map { it.toUiModel() }
    )
}

fun Task.toUiModel(): TaskUiModel {
    val zone = ZoneId.systemDefault()
    val start = Instant.ofEpochMilli(dateStart).atZone(zone).format(timeFormatter)
    val end = Instant.ofEpochMilli(dateFinish).atZone(zone).format(timeFormatter)

    return TaskUiModel(
        id = id,
        name = name,
        description = description,
        timeRange = "$start - $end"
    )
}
