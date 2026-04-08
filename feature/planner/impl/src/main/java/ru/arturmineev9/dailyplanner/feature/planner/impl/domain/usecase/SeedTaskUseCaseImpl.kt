package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase

import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.SeedTasksUseCase
import javax.inject.Inject

class SeedTasksUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : SeedTasksUseCase {
    override suspend fun invoke() = repository.seedDataIfNeeded()
}
