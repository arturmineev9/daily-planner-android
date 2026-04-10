package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.model

data class TaskUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val timeRange: String
)
