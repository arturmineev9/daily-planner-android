package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task

data class HourSlot(
    val timeLabel: String,
    val tasks: List<Task>
)
