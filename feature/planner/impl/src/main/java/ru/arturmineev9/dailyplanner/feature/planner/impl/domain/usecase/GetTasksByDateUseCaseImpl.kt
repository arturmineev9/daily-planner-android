package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase

import ru.arturmineev9.dailyplanner.core.common.datetime.DateTimeUtils
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTasksByDateUseCase
import javax.inject.Inject

class GetTasksByDateUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : GetTasksByDateUseCase {
    override fun invoke(timestamp: Long) = repository.getTasksInRange(
        start = DateTimeUtils.getStartOfDay(timestamp),
        end = DateTimeUtils.getEndOfDay(timestamp)
    )
}
