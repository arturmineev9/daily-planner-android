package ru.arturmineev9.dailyplanner.feature.planner.api.domain.model

data class AddTaskParams(
    val title: String,
    val description: String,
    val dateMillis: Long,
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int
)
