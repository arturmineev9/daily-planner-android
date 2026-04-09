package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.model

data class HourSlot(
    val timeLabel: String,
    val tasks: List<TaskUiModel>
)
