package ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi

import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEffect
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiEvent
import ru.arturmineev9.dailyplanner.core.ui.mvi.UiState

data class CreateTaskState(
    val title: String = "",
    val description: String = "",
    val selectedDateMillis: Long? = null,
    val startHour: Int = 12,
    val startMinute: Int = 0,
    val endHour: Int = 13,
    val endMinute: Int = 0,
    val isLoading: Boolean = false,
) : UiState {
    val isSaveButtonEnabled: Boolean
        get() = title.isNotBlank() && selectedDateMillis != null
}

sealed interface CreateTaskEvent : UiEvent {
    data class OnTitleChanged(val title: String) : CreateTaskEvent
    data class OnDescriptionChanged(val description: String) : CreateTaskEvent
    data class OnDateSelected(val timestamp: Long) : CreateTaskEvent
    data class OnStartTimeSelected(val hour: Int, val minute: Int) : CreateTaskEvent
    data class OnEndTimeSelected(val hour: Int, val minute: Int) : CreateTaskEvent
    object OnSaveClicked : CreateTaskEvent
    object OnBackClicked : CreateTaskEvent
}

sealed interface CreateTaskEffect : UiEffect {
    object NavigateBack : CreateTaskEffect
    data class ShowError(val message: String) : CreateTaskEffect
}
