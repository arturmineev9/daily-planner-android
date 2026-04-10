package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.mapper

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.TimeSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model.HourSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model.TaskUiModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun TimeSlot.toUiModel(): HourSlot {
    val startHour = "%02d:00".format(hourIndex)
    val endHour = "%02d:00".format(if (hourIndex == 23) 0 else hourIndex + 1)

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
