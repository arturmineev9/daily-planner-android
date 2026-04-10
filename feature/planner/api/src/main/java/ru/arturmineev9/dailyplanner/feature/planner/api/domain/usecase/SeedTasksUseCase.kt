package ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase

import ru.arturmineev9.dailyplanner.core.common.result.AppResult

interface SeedTasksUseCase {
    /** Читает JSON и заполняет БД при первом запуске */
    suspend operator fun invoke(): AppResult<Unit>
}
