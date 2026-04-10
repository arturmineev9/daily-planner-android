package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi

import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEffect
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEvent
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiState
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.model.TaskDetailInfo

data class TaskDetailState(
    val isLoading: Boolean = true,
    val taskInfo: TaskDetailInfo? = null,
    val error: String? = null
) : UiState

sealed interface TaskDetailEvent : UiEvent {
    object OnBackClicked : TaskDetailEvent
}

sealed interface TaskDetailEffect : UiEffect {
    object NavigateBack : TaskDetailEffect
}
