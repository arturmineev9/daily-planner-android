package ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.TimeSlot

interface GetTasksByDateUseCase {
    operator fun invoke(timestamp: Long): Flow<AppResult<List<TimeSlot>>>
}
