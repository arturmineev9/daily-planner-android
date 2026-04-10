package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.core.ui.mvi.BaseViewModel
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.AddTaskParams
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.AddTaskUseCase
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskEffect
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskState
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : BaseViewModel<CreateTaskState, CreateTaskEvent, CreateTaskEffect>() {

    override fun createInitialState() = CreateTaskState(
        selectedDateMillis = System.currentTimeMillis()
    )

    override fun handleEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.OnTitleChanged -> setState { copy(title = event.title) }
            is CreateTaskEvent.OnDescriptionChanged -> setState { copy(description = event.description) }
            is CreateTaskEvent.OnDateSelected -> setState { copy(selectedDateMillis = event.timestamp) }
            is CreateTaskEvent.OnStartTimeSelected -> setState {
                copy(
                    startHour = event.hour,
                    startMinute = event.minute
                )
            }

            is CreateTaskEvent.OnEndTimeSelected -> setState {
                copy(
                    endHour = event.hour,
                    endMinute = event.minute
                )
            }

            CreateTaskEvent.OnBackClicked -> setEffect { CreateTaskEffect.NavigateBack }
            CreateTaskEvent.OnSaveClicked -> saveTask()
        }
    }

    private fun saveTask() {
        val currentState = uiState.value

        if (currentState.isLoading) return
        val dateMillis = currentState.selectedDateMillis ?: return

        setState { copy(isLoading = true) }

        val params = AddTaskParams(
            title = currentState.title,
            description = currentState.description,
            dateMillis = dateMillis,
            startHour = currentState.startHour,
            startMinute = currentState.startMinute,
            endHour = currentState.endHour,
            endMinute = currentState.endMinute
        )

        viewModelScope.launch {
            when (val result = addTaskUseCase(params)) {
                is AppResult.Success -> {
                    setState { copy(isLoading = false) }
                    setEffect { CreateTaskEffect.NavigateBack }
                }

                is AppResult.Error -> {
                    setState { copy(isLoading = false) }
                    setEffect { CreateTaskEffect.ShowError(result.message ?: "Ошибка сохранения") }
                }
            }
        }
    }
}
