package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.mapper

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.TimeSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model.HourSlot

fun TimeSlot.toUiModel(): HourSlot {
    val startHour = "%02d:00".format(hourIndex)
    val endHour = "%02d:00".format(if (hourIndex == 23) 0 else hourIndex + 1)

    return HourSlot(
        timeLabel = "$startHour - $endHour",
        tasks = this.tasks
    )
}