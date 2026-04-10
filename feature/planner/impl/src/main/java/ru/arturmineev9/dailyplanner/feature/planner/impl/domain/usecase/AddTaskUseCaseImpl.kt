package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase

import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.AddTaskParams
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.AddTaskUseCase
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : AddTaskUseCase {

    override suspend fun invoke(params: AddTaskParams): AppResult<Unit> {
        val startMins = params.startHour * 60 + params.startMinute
        val endMins = params.endHour * 60 + params.endMinute
        if (endMins <= startMins) {
            return AppResult.Error(
                IllegalArgumentException(),
                "Время окончания должно быть позже времени начала"
            )
        }

        val zoneId = ZoneId.systemDefault()
        val localDate = Instant.ofEpochMilli(params.dateMillis).atZone(zoneId).toLocalDate()

        val startTimestamp = localDate.atTime(params.startHour, params.startMinute)
            .atZone(zoneId).toInstant().toEpochMilli()

        val endTimestamp = localDate.atTime(params.endHour, params.endMinute)
            .atZone(zoneId).toInstant().toEpochMilli()

        val task = Task(
            id = 0,
            name = params.title,
            description = params.description,
            dateStart = startTimestamp,
            dateFinish = endTimestamp
        )

        return repository.addTask(task)
    }
}
