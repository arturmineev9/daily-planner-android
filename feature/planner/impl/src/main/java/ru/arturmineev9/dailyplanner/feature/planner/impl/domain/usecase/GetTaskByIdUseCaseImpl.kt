package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase


import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTaskByIdUseCase
import javax.inject.Inject

class GetTaskByIdUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : GetTaskByIdUseCase {
    override suspend fun invoke(id: Int) = repository.getTaskById(id)
}
