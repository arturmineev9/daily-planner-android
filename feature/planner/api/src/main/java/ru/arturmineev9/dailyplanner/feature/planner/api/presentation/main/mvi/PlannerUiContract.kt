package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi

import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEffect
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEvent
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiState
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.model.HourSlot

data class PlannerState(
    val isLoading: Boolean = false,
    val selectedDate: Long = System.currentTimeMillis(),
    val hourSlots: List<HourSlot> = emptyList(),
    val error: String? = null
) : UiState

sealed interface PlannerEvent : UiEvent {
    data class OnDateSelected(val timestamp: Long) : PlannerEvent
    data class OnTaskClicked(val taskId: Int) : PlannerEvent
    object OnAddTaskClicked : PlannerEvent
}

sealed interface PlannerEffect : UiEffect {
    data class NavigateToDetails(val taskId: Int) : PlannerEffect
    object NavigateToCreateTask : PlannerEffect
}
