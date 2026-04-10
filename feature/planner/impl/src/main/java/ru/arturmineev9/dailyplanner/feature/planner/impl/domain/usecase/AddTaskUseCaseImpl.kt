package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : AddTaskUseCase {
    override suspend fun invoke(task: Task) = repository.addTask(task)
}
