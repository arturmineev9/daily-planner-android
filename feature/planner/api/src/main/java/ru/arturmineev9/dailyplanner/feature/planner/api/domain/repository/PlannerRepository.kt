package ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task

interface PlannerRepository {
    fun getTasksInRange(start: Long, end: Long): Flow<AppResult<List<Task>>>

    suspend fun getTaskById(id: Int): AppResult<Task>

    suspend fun addTask(task: Task): AppResult<Unit>

    suspend fun seedDataIfNeeded(): AppResult<Unit>
}
