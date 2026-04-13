package ru.arturmineev9.dailyplanner.feature.planner.impl.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.arturmineev9.dailyplanner.core.common.dispatcher.DispatchersProvider
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.data.PlannerLocalDataSource
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.impl.data.mapper.toDomain
import ru.arturmineev9.dailyplanner.feature.planner.impl.data.mapper.toEntity
import javax.inject.Inject

class PlannerRepositoryImpl @Inject constructor(
    private val localDataSource: PlannerLocalDataSource,
    private val dispatchers: DispatchersProvider
) : PlannerRepository {

    override fun getTasksInRange(start: Long, end: Long): Flow<AppResult<List<Task>>> {
        return localDataSource.getTasksForDay(start, end)
            .map { entities ->
                val domainTasks = entities.map { it.toDomain() }
                AppResult.Success(domainTasks) as AppResult<List<Task>>
            }
            .catch { e ->
                emit(AppResult.Error(e, "Ошибка при загрузке из БД"))
            }
            .flowOn(dispatchers.io)
    }

    override suspend fun getTaskById(id: Int): AppResult<Task> = withContext(dispatchers.io) {
        try {
            val entity = localDataSource.getTaskById(id)
            if (entity != null) {
                AppResult.Success(entity.toDomain())
            } else {
                AppResult.Error(Exception("Задача не найдена"))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun addTask(task: Task): AppResult<Unit> = withContext(dispatchers.io) {
        try {
            localDataSource.addTask(task.toEntity())
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Error(e, "Не удалось сохранить задачу")
        }
    }

    override suspend fun seedDataIfNeeded(): AppResult<Unit> {
        return AppResult.Success(Unit)
    }
}
