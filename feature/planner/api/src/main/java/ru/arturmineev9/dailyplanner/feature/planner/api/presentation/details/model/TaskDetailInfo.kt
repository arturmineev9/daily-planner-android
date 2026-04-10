package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.model

data class TaskDetailInfo(
    val title: String,
    val dateFull: String,
    val timeRange: String,
    val description: String
)
