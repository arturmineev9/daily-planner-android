package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.mapper

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.model.TaskDetailInfo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Task.toDetailInfo(): TaskDetailInfo {
    val zone = ZoneId.systemDefault()
    val startInstant = Instant.ofEpochMilli(dateStart).atZone(zone)
    val endInstant = Instant.ofEpochMilli(dateFinish).atZone(zone)

    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    return TaskDetailInfo(
        title = this.name,
        dateFull = startInstant.format(dateFormatter),
        timeRange = "${startInstant.format(timeFormatter)} - ${endInstant.format(timeFormatter)}",
        description = this.description.ifBlank { "Нет описания" }
    )
}
