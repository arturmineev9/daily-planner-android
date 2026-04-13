package ru.arturmineev9.dailyplanner.feature.planner.impl

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.impl.domain.usecase.GetTasksByDateUseCaseImpl
import java.time.LocalDate
import java.time.ZoneId

class GetTasksByDateUseCaseTest {

    private val repository: PlannerRepository = mockk()
    private val useCase = GetTasksByDateUseCaseImpl(repository)

    @Test
    fun `when repository returns tasks, usecase should map them to 24 hourly slots`() = runTest {
        val testDate = LocalDate.of(2025, 10, 25)
        val timestamp = testDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val task = Task(
            id = 1,
            name = "Test Task",
            description = "Desc",
            dateStart = testDate.atTime(10, 30).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            dateFinish = testDate.atTime(11, 30).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        )

        every { repository.getTasksInRange(any(), any()) } returns flowOf(AppResult.Success(listOf(task)))

        useCase(timestamp).test {
            val result = awaitItem()
            assertTrue(result is AppResult.Success)

            val slots = (result as AppResult.Success).data
            assertEquals(24, slots.size)
            assertTrue("Task should be in slot 10", slots[10].tasks.contains(task))
            assertTrue("Task should be in slot 11", slots[11].tasks.contains(task))
            assertTrue("Slot 9 should be empty", slots[9].tasks.isEmpty())

            awaitComplete()
        }
    }

    @Test
    fun `when repository returns error, usecase should emit error result`() = runTest {
        val timestamp = System.currentTimeMillis()
        val exception = RuntimeException("DB Error")
        every { repository.getTasksInRange(any(), any()) } returns flowOf(AppResult.Error(exception, "Error"))

        useCase(timestamp).test {
            val result = awaitItem()
            assertTrue(result is AppResult.Error)
            assertEquals("Error", (result as AppResult.Error).message)
            awaitComplete()
        }
    }
}
