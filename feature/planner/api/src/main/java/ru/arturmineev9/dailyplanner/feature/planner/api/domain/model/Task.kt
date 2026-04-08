package ru.arturmineev9.dailyplanner.feature.planner.api.domain.model

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String
)
