package ru.arturmineev9.dailyplanner.feature.planner.api.domain.model

data class TimeSlot(
    val hourIndex: Int,
    val tasks: List<Task>
)
