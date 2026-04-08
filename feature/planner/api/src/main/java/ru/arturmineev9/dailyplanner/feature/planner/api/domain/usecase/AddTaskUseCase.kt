package ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase

import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task

interface AddTaskUseCase {
    suspend operator fun invoke(task: Task): AppResult<Unit>
}
