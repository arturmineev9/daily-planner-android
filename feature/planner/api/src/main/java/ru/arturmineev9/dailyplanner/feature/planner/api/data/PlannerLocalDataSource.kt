package ru.arturmineev9.dailyplanner.feature.planner.api.data

import kotlinx.coroutines.flow.Flow
import ru.arturmineev9.dailyplanner.core.database.entity.TaskEntity

interface PlannerLocalDataSource {
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>
    suspend fun getTaskById(id: Int): TaskEntity?
    suspend fun addTask(task: TaskEntity)
}
