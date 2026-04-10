package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerRoute

@Serializable
data class TaskDetailRoute(val taskId: Int)

@Serializable
object CreateTaskRoute
