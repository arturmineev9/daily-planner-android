package ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task

interface PlannerRepository {
    // Получить все задачи на конкретный день
    fun getTasksByDate(timestamp: Long): Flow<AppResult<List<Task>>>

    // Получить конкретную задачу по ID (для экрана деталей)
    suspend fun getTaskById(id: Int): AppResult<Task>

    // Добавить новую задачу
    suspend fun addTask(task: Task): AppResult<Unit>

    // Инициализация данных из JSON
    suspend fun seedDataIfNeeded(): AppResult<Unit>
}
