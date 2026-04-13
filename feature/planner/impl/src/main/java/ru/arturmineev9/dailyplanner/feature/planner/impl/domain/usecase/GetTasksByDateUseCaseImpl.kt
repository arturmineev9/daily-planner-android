package ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase

import kotlinx.coroutines.flow.map
import ru.arturmineev9.dailyplanner.core.common.datetime.DateTimeUtils
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.TimeSlot
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTasksByDateUseCase
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

private const val DAY_START_HOUR = 0
private const val DAY_END_HOUR = 23
private const val NEXT_HOUR_OFFSET = 1L

class GetTasksByDateUseCaseImpl @Inject constructor(
    private val repository: PlannerRepository
) : GetTasksByDateUseCase {

    override fun invoke(timestamp: Long) = repository.getTasksInRange(
        start = DateTimeUtils.getStartOfDay(timestamp),
        end = DateTimeUtils.getEndOfDay(timestamp)
    ).map { result ->
        when (result) {
            is AppResult.Success -> AppResult.Success(mapToTimeSlots(timestamp, result.data))
            is AppResult.Error -> AppResult.Error(result.error, result.message)
        }
    }

    private fun mapToTimeSlots(timestamp: Long, tasks: List<Task>): List<TimeSlot> {
        val zoneId = ZoneId.systemDefault()
        val startZoned = Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDate().atStartOfDay(zoneId)

        return (DAY_START_HOUR..DAY_END_HOUR).map { hour ->
            val s = startZoned.plusHours(hour.toLong()).toInstant().toEpochMilli()
            val e = startZoned.plusHours(hour.toLong() + NEXT_HOUR_OFFSET).toInstant().toEpochMilli()

            TimeSlot(
                hourIndex = hour,
                tasks = tasks.filter { it.dateStart < e && it.dateFinish > s }
            )
        }
    }
}
