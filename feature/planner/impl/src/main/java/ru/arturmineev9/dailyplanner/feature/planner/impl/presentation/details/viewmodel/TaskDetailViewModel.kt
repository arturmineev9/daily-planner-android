package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.core.ui.mvi.BaseViewModel
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTaskByIdUseCase
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailEffect
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailState
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation.TaskDetailRoute
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.mapper.toDetailInfo
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTaskByIdUseCase: GetTaskByIdUseCase
) : BaseViewModel<TaskDetailState, TaskDetailEvent, TaskDetailEffect>() {

    private val navArgs = savedStateHandle.toRoute<TaskDetailRoute>()

    override fun createInitialState() = TaskDetailState()

    init {
        loadTaskDetails(navArgs.taskId)
    }

    private fun loadTaskDetails(taskId: Int) {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }

            when (val result = getTaskByIdUseCase(taskId)) {
                is AppResult.Success -> {
                    setState {
                        copy(isLoading = false, taskInfo = result.data.toDetailInfo())
                    }
                }
                is AppResult.Error -> {
                    setState {
                        copy(isLoading = false, error = result.message ?: "Ошибка загрузки")
                    }
                }
            }
        }
    }

    override fun handleEvent(event: TaskDetailEvent) {
        when (event) {
            TaskDetailEvent.OnBackClicked -> setEffect { TaskDetailEffect.NavigateBack }
        }
    }
}
