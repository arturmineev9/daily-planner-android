package ru.arturmineev9.dailyplanner.feature.planner.impl.data.datasource

import ru.arturmineev9.dailyplanner.core.database.dao.TaskDao
import ru.arturmineev9.dailyplanner.core.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import ru.arturmineev9.dailyplanner.feature.planner.api.data.PlannerLocalDataSource
import javax.inject.Inject

class PlannerLocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : PlannerLocalDataSource {
    override fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>> {
        return taskDao.getTasksForDay(startOfDay, endOfDay)
    }

    override suspend fun getTaskById(id: Int): TaskEntity? {
        return taskDao.getTaskById(id)
    }

    override suspend fun addTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }
}
